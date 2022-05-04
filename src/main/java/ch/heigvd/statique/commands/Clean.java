package ch.heigvd.statique.commands;

import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.Callable;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "clean", description = "Clean a static site")
public class Clean implements Callable<Integer> {

  @Parameters(paramLabel = "SITE", description = "The site to clean")
  public Path site;

  @Override
  public Integer call() throws IOException {
    Path build = site.resolve("build");
    Utils.deleteRecursive(build);
    return 0;
  }

}