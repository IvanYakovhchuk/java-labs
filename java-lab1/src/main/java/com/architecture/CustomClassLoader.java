package com.architecture;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CustomClassLoader extends ClassLoader {

    private final Path classPath;

    public CustomClassLoader(Path classPath) {
        super(null);
        this.classPath = classPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            String relativePath = name.replace('.', File.separatorChar) + ".class";
            Path classFile = classPath.resolve(relativePath);

            if (!Files.exists(classFile)) {
                throw new ClassNotFoundException("File not found: " + classFile);
            }

            byte[] classBytes = Files.readAllBytes(classFile);
            return defineClass(name, classBytes, 0, classBytes.length);
        }
        catch (IOException e) {
            throw new ClassNotFoundException("Error during loading a class: " + name, e);
        }
    }
}
