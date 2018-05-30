package cc.doctor.data.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;

public class FileTrigger extends OutputStreamTrigger {
    private static final Logger log = LoggerFactory.getLogger(FileTrigger.class);

    private String fileName;

    @Override
    public OutputStream outputStream() {
        File file = new File(fileName);
        if (file.exists() && file.isDirectory()) {
            file = new File(fileName, randomFileName());
        }
        try {
            return new FileOutputStream(file, true);
        } catch (FileNotFoundException e) {
            log.error("", e);
            return null;
        }
    }

    private String randomFileName() {
        return String.valueOf(new Date().getTime());
    }

    @Override
    public String name() {
        return "file";
    }
}
