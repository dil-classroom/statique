package ch.heigvd.statique.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

public class BuildTest {

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
  public void build() throws IOException {
    int initExitCode = new CommandLine(new Statique()).execute("init", site.toString());
    assertEquals(initExitCode, 0);
    int buildExitCode = new CommandLine(new Statique()).execute("build", site.toString());
    assertEquals(buildExitCode, 0);
    assertTrue(Files.exists(site.resolve("build/index.html")));
    assertTrue(Files.exists(site.resolve("build/pages/page-1.html")));
    assertTrue(Files.exists(site.resolve("build/pages/page-2.html")));
    assertTrue(Files.readString(site.resolve("build/index.html")).contains("<title>Mon site internet | Home page </title>"));
    assertTrue(Files.readString(site.resolve("build/index.html")).contains("<h1>Titre 1</h1>"));
  }
}
