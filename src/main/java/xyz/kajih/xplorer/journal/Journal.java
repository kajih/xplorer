package xyz.kajih.xplorer.journal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Journal.
 */
public class Journal {
    private final Path journalPath;
    private FileChannel channel;

    /**
     * Instantiera ny Journal
     *
     * @param filename journal sökväg
     * @throws IOException the io exception
     */
    public Journal(String filename) throws IOException {
        this.journalPath = Paths.get(filename);
        this.channel = FileChannel.open(journalPath, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.APPEND);
    }

    /**
     * Append.
     *
     * @param entry the entry
     * @throws IOException the io exception
     */
    public void append(String entry) throws IOException {
        ByteBuffer buffer = ByteBuffer.wrap((entry + System.lineSeparator()).getBytes(StandardCharsets.UTF_8));
        while (buffer.hasRemaining()) {
            channel.write(buffer);
        }
        channel.force(true); // forcera write
    }

    /**
     * Exists boolean.
     *
     * @return the boolean
     */
    public boolean exists() {
        try {
            return Files.exists(journalPath) && Files.size(journalPath) > 0;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Clear.
     *
     * @throws IOException the io exception
     */
    public void clear() throws IOException {
        close(); // Stöng kanalen före trunkering
        Files.write(journalPath, new byte[0], StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
        this.channel = FileChannel.open(journalPath, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.APPEND);
    }

    /**
     * Close.
     *
     * @throws IOException the io exception
     */
    public void close() throws IOException {
        if (channel != null && channel.isOpen()) {
            channel.close();
        }
    }

    /**
     * Reader buffered reader.
     *
     * @return the buffered reader
     * @throws IOException the io exception
     */
    public BufferedReader reader() throws IOException {
        return Files.newBufferedReader(journalPath);
    }
}
