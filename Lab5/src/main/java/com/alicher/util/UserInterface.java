package com.alicher.util;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Класс, реализующий взаимодействие с пользователем.
 */
public class UserInterface {
    /**
     * Сканнер.
     */
    private final Scanner scanner;
    /**
     * Куда идет запись.
     */
    private final Writer writer;

    private final boolean interactive;

    /**
     * Стандартный конструктор.
     *
     * @param r  откуда считывать.
     * @param w  куда записывать.
     */
    public UserInterface(Reader r, Writer w, boolean interactive) {
        this.writer = w;
        this.scanner = new Scanner(r);
        this.interactive = interactive;
    }

    /**
     * Метод, считывающий введенную пользователем строку.
     *
     * @return введенная строка.
     */
    public String read() {
        return scanner.nextLine();
    }

    public double readDouble() throws IOException {
        double input;
        while (true) {
            try {
                input = scanner.nextDouble();
                break;
            } catch (InputMismatchException e) {
                displayMessage("Incorrect input, try again");
                scanner.next();
            }
        }
        return input;
    }

    public boolean readBool() throws IOException {
        boolean input;
        while (true) {
            try {
                input = scanner.nextBoolean();
                break;
            } catch (InputMismatchException e) {
                displayMessage("Incorrect input, try again");
                scanner.next();
            }
        }
        return input;
    }

    public int readInt() throws IOException {
        int input;
        while (true) {
            try {
                input = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                displayMessage("Incorrect input, try again");
                scanner.next();
            }
        }
        return input;
    }

    /**
     * Метод, проверяющий наличие несчитанных строк.
     *
     * @return true, если они есть, иначе false.
     */
    public boolean hasNextLine() {
        return scanner.hasNextLine();
    }

    /**
     * Метод, пишущий сообщение на вывод.
     *
     * @param message сообшение.
     * @throws IOException в случае ошибки ввода/вывода.
     */
    public void write(String message) throws IOException {
        writer.write(message);
        writer.flush();
    }

    /**
     * Метод, демонстрирующий сообщение пользователю.
     *
     * @param message сообщение.
     * @throws IOException в случае ошибки ввода/вывода.
     */
    public void displayMessage(String message) throws IOException {
        write(message + "\n");
    }

    public void displayMessageNoNL(String message) throws IOException {
        write(message);
    }


    public boolean isInteractive() {
        return interactive;
    }
}