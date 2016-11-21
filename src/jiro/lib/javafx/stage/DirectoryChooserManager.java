package jiro.lib.javafx.stage;

import java.io.File;
import java.util.Optional;

import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 * DirectoryChooserで開いたディレクトリのパスを保持するクラス。
 * @author jiro
 * @version 1.1
 */
public class DirectoryChooserManager {
  private File currentDirectory;
  private final DirectoryChooser dc;

  /**
   * カレントディレクトリをDirectoryChooserの初期ディレクトリに設定するコンストラクタ。
   */
  public DirectoryChooserManager() {
    this(".");
  }

  /**
   * ファイルパスを指定するコンストラクタ
   * @param dirPath ファイルパス
   */
  public DirectoryChooserManager(String dirPath) {
    dc = new DirectoryChooser();
    File dir = new File(dirPath);
    dir = dir.exists() && dir.isDirectory() ? dir : new File(".");
    dc.setInitialDirectory(dir);
  }

  /**
   * ディレクトリ選択画面を開く。
   * 次回起動時はその選択したディレクトリを再び選択できるように
   * 初期ディレクトリが更新される。
   * @param stage Stage
   * @return 取得したディレクトリ
   */
  public Optional<File> openDirectory(Stage stage) {
    File dir = dc.showDialog(stage);
    Optional<File> dirOpt = Optional.ofNullable(dir);
    dirOpt.ifPresent(f -> {
      currentDirectory = f.getParentFile();
      dc.setInitialDirectory(currentDirectory);
    });
    return dirOpt;
  }

  /**
   * 開いたディレクトリを返す。
   * @return 開いたディレクトリ
   */
  public File getDirectory() {
    return dc.getInitialDirectory();
  }
}
