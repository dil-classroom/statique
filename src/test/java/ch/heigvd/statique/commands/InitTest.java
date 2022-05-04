package ch.heigvd.statique.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ch.heigvd.statique.Statique;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

public class InitTest {

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
  public void init() {
    int exitCode = new CommandLine(new Statique()).execute("init", site.toString());
    assertEquals(exitCode, 0);
    assertTrue(Files.exists(site.resolve("config.yaml")));
    assertTrue(Files.exists(site.resolve("index.md")));
    assertTrue(Files.exists(site.resolve("pages/page-1.md")));
    assertTrue(Files.exists(site.resolve("pages/page-2.md")));
    assertTrue(Files.exists(site.resolve("template/layout.hbs")));
    assertTrue(Files.exists(site.resolve("template/menu.hbs")));
  }
}
