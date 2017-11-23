import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GameLogic {
    private boolean whoIsTern;
    private Field field;
    public GameLogic() {
        whoIsTern = true;
        field = new Field();
    }

    public void start(){
        System.out.println("Введите координаты хода через пробел и нажмите ENTER");
        int spotWinner;
        do{
            if(whoIsTern){
                System.out.println("Ход КРЕСТИКИ");
            } else {
                System.out.println("Ход НОЛИКИ");
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                String tern = reader.readLine();
                String tempData[] = tern.split(" ");
                if(tempData.length != 2){
                    throw new IOException();
                }
                int indexI = Integer.parseInt(tempData[0]);
                int indexJ = Integer.parseInt(tempData[1]);
                if(indexI > 2 || indexJ > 2){
                    throw new IOException();
                }
                tern(indexI, indexJ);
            } catch (IOException e) {
                System.out.println("Неверный ввод");
            }
            field.grow();
            spotWinner = spotWinner();

        } while (spotWinner == 0 && field.emptyFields());

        if (field.emptyFields()) {
            if(spotWinner == -3){
                System.out.println("Выиграли КРЕСТИКИ");
            } else {
                System.out.println("Выиграли НОЛИКИ");
            }
        } else {
            System.out.println("Ничья");
        }
        System.out.println("КОНЕЦ ИГРЫ");
    }

    public boolean tern(int indexI, int indexJ){
        int value = whoIsTern ? Field.VALUE_X : Field.VALUE_O;  //проверяет чей ход

        boolean ternSuc = field.setValue(indexI,indexJ,value);  //делает ход
        if(ternSuc){                                            //проверяет, удачно ли сходил
            whoIsTern = !whoIsTern;
            return true;
        } else {
            System.out.println("Неверный ход, ячейка заблокирована");
            return false;
        }
    }

    public int spotWinner(){ //проверяет победителя
        int sum;

        // проверить вертикальные линии
        for(int i = 0; i < 3; i++){
            sum = field.getValue(0,i) + field.getValue(1,i) + field.getValue(2,i);
            if(sum == 3 || sum == -3){
                return sum;
            }
        }

        // проверить горизонтальные линии
        for(int i = 0; i < 3; i++){
            sum = field.getValue(i, 0) + field.getValue(i, 1) + field.getValue(i, 2);
            if(sum == 3 || sum == -3){
                return sum;
            }
        }

        //проверяем оставшиеся кобинации по диагонали
        sum = field.getValue(0,0) + field.getValue(1,1) + field.getValue(2,2);
        if(sum == 3 || sum == -3){
            return sum;
        }
        sum = field.getValue(0,2) + field.getValue(1,1) + field.getValue(2,0);
        if(sum == 3 || sum == -3){
            return sum;
        }
        return 0;
    }


}
