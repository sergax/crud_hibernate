package com.sergax.crudhibernate.view;

import com.sergax.crudhibernate.controller.PostController;
import com.sergax.crudhibernate.controller.TagController;
import com.sergax.crudhibernate.controller.WriterController;
import com.sergax.crudhibernate.util.Messages;

import java.util.Scanner;

public class ConsoleRunner {
    private GeneralView tagView;
    private GeneralView postView;
    private GeneralView writerView;

    private final Scanner sc = new Scanner(System.in);

    public ConsoleRunner() {
        try {
            TagController tagController = new TagController();
            PostController postController = new PostController();
            WriterController writerController = new WriterController();

            tagView = new TagView(tagController, sc);
            postView = new PostView(postController, tagController, writerController, sc);
            writerView = new WriterView(writerController, postController, sc);

        } catch (Exception e) {
            System.err.println(Messages.DATA_DAMAGED.getMessage());
        }
    }

    public void run() {
        boolean isExit = false;
        do {
            System.out.println(Messages.ACTION_LIST.getMessage());
            String response = sc.next();
            switch (response) {
                case "1" -> tagView.show();
                case "2" -> postView.show();
                case "3" -> writerView.show();
                case "4" -> isExit = true;
                default -> System.out.println(Messages.ERROR_INPUT.getMessage());
            }
        } while (!isExit);
        sc.close();
    }

    public static void main(String[] args) {
        try {
            ConsoleRunner runner = new ConsoleRunner();
            runner.run();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
