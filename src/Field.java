public class Field {
    public static final int VALUE_O = 1;
    public static final int VALUE_X = -1;

    private int valueInGrid[][];

    public Field() {
        valueInGrid = new int[3][3];
        growStart();
    }

    private void growStart(){
        grow();
    }

    public void grow(){
        for(int i = 0; i < 4; i++){
            System.out.println("|---|---|---|   |---|---|---|");
            if(i >= 3) break;
            for(int j = 0; j < 8; j++){
                System.out.print("|");
                if((j > 3) && (j < 7)) {
                    System.out.print((i) + " " + (j - 4));
                }
                if(j == 3)System.out.print("   ");
                if(j >= 3)continue;
                int temp = valueInGrid[i][j];
                if(temp == VALUE_X) System.out.print(" X ");
                else if(temp == VALUE_O) System.out.print(" O ");
                else System.out.print("   ");
            }
            System.out.println();
        }
    }

    public boolean setValue(int indexI, int indexJ, int value){
        if(valueInGrid[indexI][indexJ] == 0){
            valueInGrid[indexI][indexJ] = value;
            return true;
        }
        return false;
    }

    public int getValue(int indexI, int indexJ){
        return valueInGrid[indexI][indexJ];
    }

    public boolean emptyFields(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                int temp = valueInGrid[i][j];
                if(temp == 0) return true;
            }
        }

        return false;
    }
}
