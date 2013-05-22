package org.hanuna.rusakov;

import org.hanuna.rusakov.frame.MainForm;
import org.hanuna.rusakov.frame.RandomMainProcessor;

/**
 * @author: erokhins
 */
public class Main {
    public static void main(String[] args) {
        new MainForm(new RandomMainProcessor()).setVisible(true);
    }
}
