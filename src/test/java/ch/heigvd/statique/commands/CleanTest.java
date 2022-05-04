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

public class CleanTest {

  Path site;
  Path build;
  Path f1;
  Path f2;

  @BeforeEach
  void setUp() throws IOException {
    site = Files.createTempDirectory(Paths.get("."), "site_");
    build = Files.createDirectories(site.resolve("build"));
    f1 = Files.createFile(build.resolve("f1"));
    f2 = Files.createFile(build.resolve("f2"));
  }

  @AfterEach
  void tearDown() throws IOException {
    Utils.deleteRecursive(site);
  }

  @Test
  public void clean() {
    assertTrue(Files.exists(f1));
    assertTrue(Files.exists(f2));
    assertTrue(Files.exists(build));
    int exitCode = new CommandLine(new Statique()).execute("clean", site.toString());
    assertEquals(exitCode, 0);
    assertFalse(Files.exists(f1));
    assertFalse(Files.exists(f2));
    assertFalse(Files.exists(build));
  }

}
