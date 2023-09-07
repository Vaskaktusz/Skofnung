package application.converter;

import jakarta.persistence.AttributeConverter;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

public final class DatabaseEncryptor implements AttributeConverter<String, String> {
    private final TextEncryptor textEncryptor = Encryptors.text(
            System.getProperty("spring.security.converter.password"),
            System.getProperty("spring.security.converter.salt")
    );

    @Override
    public String convertToDatabaseColumn(String attribute) {
        return textEncryptor.encrypt(attribute);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return textEncryptor.decrypt(dbData);
    }
}