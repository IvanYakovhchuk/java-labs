package com.architecture;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.nio.file.*;

public class Task2 {
    private static final Path SRC_DIR = Path.of("C:\\Users\\yakov\\OneDrive\\Робочий стіл\\java-labs\\java-lab1\\src\\main\\java");
    private static final Path CLASS_DIR = Path.of("C:\\Users\\yakov\\OneDrive\\Робочий стіл\\java-labs\\java-lab1\\target\\classes");

    public static void main(String[] args) throws Exception {
        String className = "com.architecture.TestModule";

        for (int i = 0; i < 100; i++) {
            CustomClassLoader loader = new CustomClassLoader(CLASS_DIR);
            Class<?> clazz = loader.loadClass(className);
            Object testModule = clazz.getDeclaredConstructor().newInstance();

            System.out.println(testModule);

            watchFile(SRC_DIR.resolve(className.replace('.', File.separatorChar) + ".java"));

        }
    }

    private static void compile(String className) {
        Path javaFile = SRC_DIR.resolve(className.replace('.', File.separatorChar) + ".java");

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        int result = compiler.run(null, null, null,
                javaFile.toString(),
                "-d", CLASS_DIR.toString());

        if (result != 0) {
            System.err.println("Compilation error!");
        }
    }

    private static void watchFile(Path file) throws Exception {
        WatchService watchService = FileSystems.getDefault().newWatchService();
        Path dir = file.getParent();
        dir.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);

        WatchKey key = watchService.take();
        for (WatchEvent<?> event : key.pollEvents()) {
            Path changed = (Path) event.context();
            if (changed.toString().equals(file.getFileName().toString() + "~")) {
                System.out.println("File changed, recompilation...");
                compile("com.architecture.TestModule");
            }
        }
        key.reset();
    }
}

