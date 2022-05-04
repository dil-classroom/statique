package ch.heigvd.statique.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ch.heigvd.statique.Statique;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

public class ServeTest {

  Path site;

  @BeforeEach
  void setUp() throws IOException {
    site = Files.createTempDirectory(Paths.get("."), "site_");
  }

  @AfterEach
  void tearDown() throws IOException {
    Utils.deleteRecursive(site);
  }

  @Test
  public void serve() throws IOException {
    int initExitCode = new CommandLine(new Statique()).execute("init", site.toString());
    assertEquals(initExitCode, 0);
    int serveExitCode = new CommandLine(new Statique()).execute("serve", site.toString());
    assertEquals(serveExitCode, 0);
    String homepage = new String(new URL("http://localhost:8080/").openStream().readAllBytes());
    assertTrue(homepage.contains("<title>Mon site internet | Home page </title>"));
  }
}
