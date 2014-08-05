package y6b.Gaming.Games2DLib.Graphics;

/**
 * Created by Yazan on 8/4/14.
 */
public class Matrix {

    private int cols;
    private int rows;

    public float [][] elements;

    public Matrix(int rows, int cols) {
        this.cols = cols;
        this.rows = rows;

        elements = new float[rows][cols];

        for(int i=0;i<rows;i++)
            for(int j=0;j<cols;j++)
                elements[i][j] = 0;
    }

    public Matrix(int size) {
        this.cols = size;
        this.rows = size;

        elements = new float[rows][cols];

        for(int i=0;i<rows;i++)
            for(int j=0;j<cols;j++)
                elements[i][j] = 0;
    }

    public Matrix(){
        elements = null;
    }

    public boolean isEmpty(){
        return elements == null;
    }

    @Override
    public boolean equals(Object o) {

        if(!(o instanceof Matrix) || o != null)
            return false;

        Matrix other = (Matrix)o;

        if(this.cols != other.cols || this.rows != other.rows)
        return false;

        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < this.cols; ++j) {
                if(this.elements[i][j] != other.elements[i][j])
                    return false;
            }
        }

        return true;
    }

    public boolean isSquared(){
        return rows == cols;
    }

    public void setDiagonal(float value){
        if(!isSquared())
            return;

        for (int i = 0; i < rows; ++i) {
            this.elements[i][i] = value;
        }
    }



    public static Matrix add(Matrix mat1,Matrix mat2){
        if(mat1.cols != mat2.cols || mat1.rows != mat2.rows)
            return Empty;

        Matrix result = new Matrix(mat2.rows,mat2.cols);

        for(int i = 0;i < mat1.rows;i++){
            for(int j = 0; j < mat1.cols;j++){
                result.elements[i][j] = mat1.elements[i][j] + mat2.elements[i][j];
            }
        }

        return result;
    }

    public static Matrix subtract(Matrix mat1,Matrix mat2){
        if(mat1.cols != mat2.cols || mat1.rows != mat2.rows)
            return Empty;

        Matrix result = new Matrix(mat2.rows,mat2.cols);

        for(int i = 0;i < mat1.rows;i++){
            for(int j = 0; j < mat1.cols;j++){
                result.elements[i][j] = mat1.elements[i][j] - mat2.elements[i][j];
            }
        }

        return result;
    }


    public static Matrix multiply(Matrix mat1,float value){
        Matrix result = new Matrix(mat1.rows,mat1.cols);

        for(int i = 0;i < mat1.rows;i++){
            for(int j = 0; j < mat1.cols;j++){
                result.elements[i][j] = mat1.elements[i][j] * value;
            }
        }

        return result;
    }

    public static Matrix multiply(Matrix mat1,Matrix mat2){
        if(mat1.cols != mat2.rows)
            return Empty;

        Matrix result = new Matrix(mat1.rows,mat1.cols);

        for(int i = 0;i < mat1.rows;i++){
            for (int k = 0; k < mat1.cols; ++k) {
                float sum = 0;
                for (int j = 0; j < mat1.rows; ++j) {
                    sum += mat1.elements[i][j] * mat1.elements[j][k];
                }

                result.elements[i][k] = sum;
            }
        }

        return result;
    }

    public static Matrix Divide(Matrix mat,float value){
        Matrix result = new Matrix(mat.rows,mat.cols);

        for(int i = 0;i < mat.rows;i++){
            for(int j = 0; j < mat.cols;j++){
                result.elements[i][j] = mat.elements[i][j] / value;
            }
        }

        return result;
    }

    public static Matrix transpose(Matrix mat){
        Matrix result = new Matrix(mat.cols,mat.rows);

        for (int i = 0; i < mat.rows; ++i) {
            for (int j = 0; j < mat.cols; ++j) {
                result.elements[j][i] = mat.elements[i][j];
            }
        }

        return result;
    }


    public static Matrix createRandom(int rows,int cols,double seed){
        Matrix mat = new Matrix(rows,cols);

        for(int i = 0;i < rows;i++){
            for(int j = 0; j < cols;j++){
                mat.elements[i][j] = (float)(Math.random() % seed);
            }
        }

        return mat;
    }


    public static final Matrix Empty = new Matrix();
}
