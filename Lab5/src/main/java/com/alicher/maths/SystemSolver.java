package com.alicher.maths;

import com.alicher.models.Matrix;

public class SystemSolver {

    public double[] solveSystem(Matrix triangularMatrix) {
        if (triangularMatrix == null)
            return null;
        double[] variables = new double[triangularMatrix.getRows()];
        for (int i = triangularMatrix.getRows() - 1; i >= 0; i--) {
            double sub = 0;
            for (int j = 0; j < triangularMatrix.getColumns() - 1; j++) {
                if (j != i)
                    sub += triangularMatrix.getElement(i, j) * variables[j];
            }
            variables[i] = (triangularMatrix.getElement(i, triangularMatrix.getColumns() - 1) - sub) / triangularMatrix.getElement(i, i);
        }
        return variables;
    }

    public Matrix findTriangularMatrix(Matrix matrix) {
        int currentRowsRemoved = 0;
        int initialRows = matrix.getRows();
        int initialColumns = matrix.getColumns();
        double[][] mainElementRows = new double[matrix.getRows()][matrix.getColumns()];
        for (int i = 0; i < initialRows; i++) {
            double[] maxElementData = matrix.findColumnAbsMaxElement(i);
            if (maxElementData[0] == 0)
                return null;
            double[] multipliers = matrix.findMultipliers(maxElementData[0], (int) maxElementData[2]);
            double[] mainElementRow = matrix.getMatrixRow((int) maxElementData[1]);
            mainElementRows = fillRowIntoArray(mainElementRow, mainElementRows, currentRowsRemoved);
            matrix.modifyMatrix(multipliers, mainElementRow);
            matrix.removeMatrixRow((int) maxElementData[1]);
            currentRowsRemoved++;
        }
        Matrix triangularMatrix = new Matrix(initialRows, initialColumns);
        triangularMatrix.setElements(mainElementRows);
        return triangularMatrix;
    }

    public double[][] fillRowIntoArray(double[] row, double[][] array, int rowsRemoved) {
        System.arraycopy(row, 0, array[rowsRemoved], 0, row.length);
        return array;
    }
}