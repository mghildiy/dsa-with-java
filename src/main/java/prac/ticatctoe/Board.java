package prac.ticatctoe;


import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board {
    //int numRows;
    //int numColumns;
    CellRange[] rows;
    CellRange[] columns;
    CellRange[] diagonals;

    Board(int gridSize) {
        generateGrid(gridSize);
    }

    record Cell(int row, int col, CV val){
        public Cell withVal(CV newVal) {
            return new Cell(row(), col(), newVal);
        }
    }

    record CellRange(Cell[] cells, int countX, int countO){
        public CellRange withX(int newCountX) {
            return new CellRange(cells(), newCountX, countO());
        }

        public CellRange withO(int newCountO) {
            return new CellRange(cells(), countX(), newCountO);
        }
    }
    enum CV {
        E, X, O
    }
    enum Result {
        WIN, DRAW, INCOMPLETE
    }

    public Result move(int r, int c, CV inputVal) {
        CellRange row = rows[r];
        CellRange col = columns[c];
        int target = rows[r].cells.length;

        if(inputVal == CV.X) {
            rows[r].cells[c] = rows[r].cells[c].withVal(CV.X);
            rows[r] = row.withX(row.countX + 1);
            if(row.countX == target) return Result.WIN;
            columns[c] = col.withX(col.countX + 1);
            if(col.countX == target) return Result.WIN;
            if(r == c){
                diagonals[0] = diagonals[0].withX(diagonals[0].countX + 1);
                if(diagonals[0].countX == target) return Result.WIN;
            }
            if(r + c == rows.length) {
                diagonals[1] = diagonals[1].withX(diagonals[1].countX + 1);
                if(diagonals[1].countX == target) return Result.WIN;
            }
            if
            return Result.DRAW;
        }
        if(inputVal == CV.O) {
            rows[r].cells[c] = rows[r].cells[c].withVal(CV.O);
            rows[r] = row.withO(row.countO + 1);
            columns[c] = col.withO(col.countO + 1);
            if(r == c){
                diagonals[0] = diagonals[0].withO(diagonals[0].countO + 1);
            }
            if(r + c == rows.length) {
                diagonals[1] = diagonals[1].withO(diagonals[1].countO + 1);
            }
        }

    }

    private void generateGrid(int gridSize) {
        rows = new CellRange[gridSize];
        columns = new CellRange[gridSize];
        diagonals = new CellRange[2];
        IntStream.range(0, gridSize)
                .forEach(r -> {
                    Cell[] cells = (Cell[]) IntStream.range(0, gridSize)
                            .mapToObj(c -> {
                                CellRange col = columns[c];
                                Cell cell = new Cell(r, c, CV.E);
                                col.cells[r] = cell;
                                if(r == c) {
                                    diagonals[0].cells[r] = cell;
                                } else {
                                    if(r + c == gridSize) {
                                        diagonals[1].cells[r] = cell;
                                    }
                                }
                                return cell;
                            })
                            .collect(Collectors.toList())
                            .toArray();
                    CellRange row = new CellRange(cells, 0, 0);
                    rows[r] = row;
                });
    }
}
