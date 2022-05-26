package com.alicher.models;

public class Matrix {

    private double[][] elements;

    private int rows;

    private int columns;

    public Matrix(int rows, int columns) {

        if (rows <= 0 || columns <= 0)
            throw new IllegalArgumentException("Incorrect matrix dimensions");

        this.rows = rows;
        this.columns = columns;
        this.elements = new double[rows][columns];

    }

    public Matrix(int size, double[][] elements) {

        if (size <= 0)
            throw new IllegalArgumentException("Incorrect matrix dimensions");

        this.rows = size;
        this.columns = size+1;
        this.elements = elements;

    }

    public double[] findColumnAbsMaxElement(int column) {
        double[] maxElementData = new double[3];
        for (int i = 0; i < rows; i++) {
            if (Math.abs(elements[i][column]) > Math.abs(maxElementData[0])) {
                maxElementData[0] = elements[i][column];
                maxElementData[1] = i;
                maxElementData[2] = column;
            }
        }
        return maxElementData;
    }

    public double[] getMatrixRow(int row) {
        double[] rowElements = new double[columns];
        System.arraycopy(elements[row], 0, rowElements, 0, columns);
        return rowElements;
    }

    public double findRowSum(int row) {
        double sum = 0;
        for (int i = 0; i < columns; i++) {
            sum += elements[row][i];
        }
        return sum;
    }

    public double[] findMatrixSums() {
        double[] sums = new double[rows];
        for (int i = 0; i < rows; i++) {
            sums[i] = findRowSum(i);
        }
        return sums;
    }

    public double[] findMultipliers(double maxElement, int mainElementColumn) {
        double[] multipliers = new double[rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                multipliers[i] = -1 * elements[i][mainElementColumn] / maxElement;
            }
        }
        return multipliers;
    }

    public void modifyMatrix(double[] multipliers, double[] mainElementRow) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                elements[i][j] = elements[i][j] + mainElementRow[j] * multipliers[i];
            }
        }
    }

    public void removeMatrixRow(int row) {
        for (int i = row; i < rows - 1; i++) {
            if (columns >= 0) System.arraycopy(elements[i + 1], 0, elements[i], 0, columns);
        }
        rows = rows - 1;
    }

    public void removeMatrixColumn(int column) {
        for (int i = column; i < columns - 1; i++) {
            for (int j = 0; j < rows; j++) {
                elements[j][i] = elements[j][i+1];
            }
        }
        columns--;
    }

    public void setElements(double[][] elements) {
        this.elements = elements;
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }

    public double[][] getElements() {
        return elements;
    }

    public double getElement(int i, int j) {
        return this.elements[i][j];
    }

    public double findDiagonalMatrixDet() {
        double det = 1;
        for (int i = 0; i < rows; i++) {
            det = det * elements[i][i];
        }
        return det;
    }
}

