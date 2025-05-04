package max;

import Data.DBWorker;
import view.MainWindow;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        new DBWorker();
        new MainWindow();
    }
}