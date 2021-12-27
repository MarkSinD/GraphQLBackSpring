package com.example.graphQLSpingBack.service;

import com.example.graphQLSpingBack.entity.PersonAvatarFile;
import com.example.graphQLSpingBack.exception.PersonAvatarDeleteFailedException;
import com.example.graphQLSpingBack.exception.PersonAvatarUploadFailedException;
import com.example.graphQLSpingBack.properties.PersonAvatarProperties;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.text.MessageFormat;
import java.time.Clock;
import java.util.Optional;

import static java.nio.charset.Charset.defaultCharset;

@Service
public class PersonAvatarService {
    private final Clock clock;
    private final MessageDigest messageDigest;
    private final PersonAvatarProperties personAvatarProperties;
    private final ResourceLoader resourceLoader;

    public PersonAvatarService(Clock clock, MessageDigest messageDigest, PersonAvatarProperties personAvatarProperties, ResourceLoader resourceLoader) {
        this.clock = clock;
        this.messageDigest = messageDigest;
        this.personAvatarProperties = personAvatarProperties;
        this.resourceLoader = resourceLoader;
    }

    public String updateAvatar(Integer personId, Part avatar){
        try {
            System.out.println("PersonId: " + personId);
            System.out.println("Avatar: " + avatar.getSubmittedFileName());
            System.out.println("Clock: " + clock);
            System.out.println("MessageDigest: " + messageDigest);
            System.out.println("PersonAvatarProperties: " + personAvatarProperties);
            System.out.println("ResourceLoader: " + resourceLoader);

            PersonAvatarFile file = new PersonAvatarFile(avatar, personId, clock);
            BufferedImage scaled = file.scale(personAvatarProperties.getMaxDimensions());
            String filename = file.getFilename(this::hash);
            ImageIO.write(scaled, file.getType(), getLocation(filename));
            return filename;
        } catch (IOException ex) {
            throw new PersonAvatarUploadFailedException(avatar.getSubmittedFileName(), ex);
        }
    }

    public boolean deleteAvatar(String avatarLocation) {
        try {
            return avatarLocation != null && getLocation(avatarLocation).delete();
        } catch (IOException ex) {
            throw new PersonAvatarDeleteFailedException(ex);
        }
    }

    public String getPublicURL(String avatarLocation){
        System.out.println("AvatarLocation: " + avatarLocation);
        String location = Optional
                .ofNullable(avatarLocation)
                .orElse(personAvatarProperties.getDefaultAvatar());
        System.out.println(MessageFormat.format("{0}/avatar/{1}", personAvatarProperties.getBaseDomain(), location));
        return MessageFormat.format("{0}/avatar/{1}", personAvatarProperties.getBaseDomain(), location);
    }

    private File getLocation(String filename) throws IOException {
        return new File(resourceLoader.getResource(personAvatarProperties.getLocation()).getFile(), filename);
    }

    private String hash(String identifier) {
        byte[] digest = messageDigest.digest(identifier.getBytes(defaultCharset()));
        String result = "";
        for (byte digestByte : digest) {
            result = result + getHexadecimal(digestByte);
        }
        return result;
    }

    private String getHexadecimal(byte digestByte) {
        return String.format("%02x", digestByte);
    }

}
