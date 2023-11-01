package application.converter;

import jakarta.persistence.AttributeConverter;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

public final class DatabaseEncryptor implements AttributeConverter<String, String>, EnvironmentAware {
    private TextEncryptor textEncryptor;

    @Override
    public String convertToDatabaseColumn(String attribute) {
        return textEncryptor.encrypt(attribute);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return textEncryptor.decrypt(dbData);
    }

    @Override
    public void setEnvironment(Environment environment) {
        textEncryptor = Encryptors.text(
                environment.getProperty("spring.security.password"),
                environment.getProperty("spring.security.salt")
        );
    }
}