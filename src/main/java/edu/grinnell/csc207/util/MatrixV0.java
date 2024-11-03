package edu.grinnell.csc207.util;

/**
 * An implementation of two-dimensional matrices.
 *
 * @author Nicole Moreno Gonzalez
 * @author Samuel A. Rebelsky
 *
 * @param <T>
 *   The type of values stored in the matrix.
 */
public class MatrixV0<T> implements Matrix<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * Matrix declaration.
   */
  private T[][] contents;
  /**
   * Default value of the Matrix.
   */
  private T defaultValue;
  /**
   * Height of the Matrix.
   */
  private int height;
  /**
   * Width of the Matrix.
   */
  private int width;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new matrix of the specified width and height with the
   * given value as the default.
   *
   * @param width
   *   The width of the matrix.
   * @param height
   *   The height of the matrix.
   * @param def
   *   The default value, used to fill all the cells.
   *
   * @throws NegativeArraySizeException
   *   If either the width or height are negative.
   */
  @SuppressWarnings("unchecked")
  public MatrixV0(int width, int height, T def) {
    if (width < 0 || height < 0) {
      throw new NegativeArraySizeException("Matrix dimensions must be non-negative.");
    } // if
    this.width = width;
    this.height = height;
    this.defaultValue = def;
    this.contents = (T[][]) new Object[this.height][this.width];
    for (int row = 0; row < this.height; row++) {
      for (int col = 0; col < this.width; col++) {
        this.contents[row][col] = this.defaultValue;
      } // for
    } // for
  } // MatrixV0(int, int, T)

  /**
   * Create a new matrix of the specified width and height with
   * null as the default value.
   *
   * @param width
   *   The width of the matrix.
   * @param height
   *   The height of the matrix.
   *
   * @throws NegativeArraySizeException
   *   If either the width or height are negative.
   */
  public MatrixV0(int width, int height) {
    this(width, height, null);
  } // MatrixV0

  // +--------------+------------------------------------------------
  // | Core methods |
  // +--------------+

  /**
   * Get the element at the given row and column.
   *
   * @param row
   *   The row of the element.
   * @param col
   *   The column of the element.
   *
   * @return the value at the specified location.
   *
   * @throws IndexOutOfBoundsException
   *   If either the row or column is out of reasonable bounds.
   */
  @Override
  public T get(int row, int col) {
    if (row < 0 || row >= this.height || col < 0 || col >= this.width) {
      throw new IndexOutOfBoundsException("Invalid row or column index.");
    } // if
    return this.contents[row][col];
  } // get(int, int)

  /**
   * Set the element at the given row and column.
   *
   * @param row
   *   The row of the element.
   * @param col
   *   The column of the element.
   * @param val
   *   The value to set.
   *
   * @throws IndexOutOfBoundsException
   *   If either the row or column is out of reasonable bounds.
   */
  @Override
  public void set(int row, int col, T val) {
    if (row < 0 || row >= this.height || col < 0 || col >= this.width) {
      throw new IndexOutOfBoundsException("Invalid row or column index.");
    } // if
    this.contents[row][col] = val;
  } // set(int, int, T)

  /**
   * Determine the number of rows in the matrix.
   *
   * @return the number of rows.
   */
  @Override
  public int height() {
    return this.height;
  } // height()

  /**
   * Determine the number of columns in the matrix.
   *
   * @return the number of columns.
   */
  @Override
  public int width() {
    return this.width;
  } // width()

  /**
   * Insert a row filled with the default value.
   *
   * @param row
   *   The number of the row to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than the height.
   */
  @Override
  @SuppressWarnings("unchecked")
  public void insertRow(int row) {
    if (row < 0 || row > this.height) {
      throw new IndexOutOfBoundsException("Row index out of bounds.");
    } // if
    T[][] newContents = (T[][]) new Object[this.height + 1][this.width];
    System.arraycopy(this.contents, 0, newContents, 0, row);
    newContents[row] = (T[]) new Object[this.width];
    for (int col = 0; col < this.width; col++) {
      newContents[row][col] = this.defaultValue;
    } // for
    System.arraycopy(this.contents, row, newContents, row + 1, this.height - row);
    this.contents = newContents;
    this.height++;
  } // insertRow(int)

  /**
   * Insert a row filled with the specified values.
   *
   * @param row
   *   The number of the row to insert.
   * @param vals
   *   The values to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than the height.
   * @throws ArraySizeException
   *   If the size of vals is not the same as the width of the matrix.
   */
  @Override
  public void insertRow(int row, T[] vals) throws ArraySizeException {
    if (row < 0 || row > this.height) {
      throw new IndexOutOfBoundsException("Row index out of bounds.");
    } // if
    if (vals.length != this.width) {
      throw new ArraySizeException("Array size does not match matrix width.");
    } // if
    this.insertRow(row);
    System.arraycopy(vals, 0, this.contents[row], 0, this.width);
  } // insertRow(int, T[])

  /**
   * Insert a column filled with the default value.
   *
   * @param col
   *   The number of the column to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than the width.
   */
  @Override
  @SuppressWarnings("unchecked")
  public void insertCol(int col) {
    if (col < 0 || col > this.width) {
      throw new IndexOutOfBoundsException("Column index out of bounds.");
    } // if
    for (int row = 0; row < this.height; row++) {
      T[] newRow = (T[]) new Object[this.width + 1];
      System.arraycopy(this.contents[row], 0, newRow, 0, col);
      newRow[col] = this.defaultValue;
      System.arraycopy(this.contents[row], col, newRow, col + 1, this.width - col);
      this.contents[row] = newRow;
    } // for
    this.width++;
  } // insertCol(int)

  /**
   * Insert a column filled with the specified values.
   *
   * @param col
   *   The number of the column to insert.
   * @param vals
   *   The values to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than the width.
   * @throws ArraySizeException
   *   If the size of vals is not the same as the height of the matrix.
   */
  @Override
  public void insertCol(int col, T[] vals) throws ArraySizeException {
    if (col < 0 || col > this.width) {
      throw new IndexOutOfBoundsException("Column index out of bounds.");
    } // if
    if (vals.length != this.height) {
      throw new ArraySizeException("Array size does not match matrix height.");
    } // if
    this.insertCol(col);
    for (int row = 0; row < this.height; row++) {
      this.contents[row][col] = vals[row];
    } // for
  } // insertCol(int, T[])

  /**
   * Delete a row.
   *
   * @param row
   *   The number of the row to delete.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than or equal to the height.
   */
  @Override
  @SuppressWarnings("unchecked")
  public void deleteRow(int row) {
    if (row < 0 || row >= this.height) {
      throw new IndexOutOfBoundsException("Row index out of bounds.");
    } // if
    T[][] newContents = (T[][]) new Object[this.height - 1][this.width];
    System.arraycopy(this.contents, 0, newContents, 0, row);
    System.arraycopy(this.contents, row + 1, newContents, row, this.height - row - 1);
    this.contents = newContents;
    this.height--;
  } // deleteRow(int)

  /**
   * Delete a column.
   *
   * @param col
   *   The number of the column to delete.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than or equal to the width.
   */
  @Override
  @SuppressWarnings("unchecked")
  public void deleteCol(int col) {
    if (col < 0 || col >= this.width) {
      throw new IndexOutOfBoundsException("Column index out of bounds.");
    } // if
    for (int row = 0; row < this.height; row++) {
      T[] newRow = (T[]) new Object[this.width - 1];
      // Copy elements before the deleted column
      System.arraycopy(this.contents[row], 0, newRow, 0, col);
      // Copy elements after the deleted column
      System.arraycopy(this.contents[row], col + 1, newRow, col, this.width - col - 1);
      this.contents[row] = newRow;
    } // for
    this.width--;
  } // deleteCol(int)

  /**
   * Fill a rectangular region of the matrix.
   *
   * @param startRow
   *   The top edge / row to start with (inclusive).
   * @param startCol
   *   The left edge / column to start with (inclusive).
   * @param endRow
   *   The bottom edge / row to stop with (exclusive).
   * @param endCol
   *   The right edge / column to stop with (exclusive).
   * @param val
   *   The value to store.
   *
   * @throws IndexOutOfBoundsException
   *   If the rows or columns are inappropriate.
   */
  @Override
  public void fillRegion(int startRow, int startCol, int endRow, int endCol,
      T val) {
    if (startRow < 0 || startCol < 0 || endRow > this.height || endCol > this.width) {
      throw new IndexOutOfBoundsException("Region bounds are invalid.");
    } // if
    for (int row = startRow; row < endRow; row++) {
      for (int col = startCol; col < endCol; col++) {
        this.contents[row][col] = val;
      } // for
    } // for
  } // fillRegion(int, int, int, int, T)

  /**
   * Fill a line (horizontal, vertical, diagonal).
   *
   * @param startRow
   *   The row to start with (inclusive).
   * @param startCol
   *   The column to start with (inclusive).
   * @param deltaRow
   *   How much to change the row in each step.
   * @param deltaCol
   *   How much to change the column in each step.
   * @param endRow
   *   The row to stop with (exclusive).
   * @param endCol
   *   The column to stop with (exclusive).
   * @param val
   *   The value to store.
   *
   * @throws IndexOutOfBoundsException
   *   If the rows or columns are inappropriate.
   */
  @Override
  public void fillLine(int startRow, int startCol, int deltaRow, int deltaCol,
      int endRow, int endCol, T val) {
    if (startRow < 0 || startCol < 0 || endRow > this.height || endCol > this.width) {
      throw new IndexOutOfBoundsException("Line bounds are invalid.");
    } // if
    int row = startRow;
    int col = startCol;
    while (row < endRow && col < endCol) {
      this.contents[row][col] = val;
      row += deltaRow;
      col += deltaCol;
    } // while
  } // fillLine(int, int, int, int, int, int, T)

  /**
   * A make a copy of the matrix. May share references (e.g., if individual
   * elements are mutable, mutating them in one matrix may affect the other
   * matrix) or may not.
   *
   * @return a copy of the matrix.
   */
  @Override
  public Matrix<T> clone() {
    MatrixV0<T> copy = new MatrixV0<>(this.width, this.height, this.defaultValue);
    for (int row = 0; row < this.height; row++) {
      System.arraycopy(this.contents[row], 0, copy.contents[row], 0, this.width);
    } // for
    return copy;
  } // clone()

  /**
   * Determine if this object is equal to another object.
   *
   * @param other
   *   The object to compare.
   *
   * @return true if the other object is a matrix with the same width,
   * height, and equal elements; false otherwise.
   */
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof MatrixV0)) {
      return false;
    } // if
    MatrixV0<?> otherMatrix = (MatrixV0<?>) other;
    if (otherMatrix.width != this.width || otherMatrix.height != this.height) {
      return false;
    } // if
    for (int row = 0; row < this.height; row++) {
      for (int col = 0; col < this.width; col++) {
        if (this.contents[row][col] == null) {
          if (otherMatrix.contents[row][col] != null) {
            return false;
          } // if
        } else if (!this.contents[row][col].equals(otherMatrix.contents[row][col])) {
          return false;
        } // else if
      } // for
    } // for
    return true;
  } // equals(Object)

  /**
   * Compute a hash code for this matrix. Included because any object
   * that implements `equals` is expected to implement `hashCode` and
   * ensure that the hash codes for two equal objects are the same.
   *
   * @return the hash code.
   */
  @Override
  public int hashCode() {
    int multiplier = 7;
    int code = this.width() + multiplier * this.height();
    for (int row = 0; row < this.height(); row++) {
      for (int col = 0; col < this.width(); col++) {
        T val = this.get(row, col);
        if (val != null) {
          // It's okay if the following computation overflows, since
          // it will overflow uniformly.
          code = code * multiplier + val.hashCode();
        } // if
      } // for col
    } // for row
    return code;
  } // hashCode()
} // class MatrixV0
