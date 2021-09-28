import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Start  {
    public static void main(String[] args) {

        Scanner console = new Scanner(System.in);
        System.out.print("Calculation please... ");
        String vyrazhenie = console.nextLine();
        vyrazhenie = vyrazhenie.toUpperCase();
        vyrazhenie = vyrazhenie.trim();
        System.out.println(vyrazhenie);
        int dlina = vyrazhenie.length();
        boolean isArabNum = true;
        String first_op = "";
        String operation = "";
        String second_op = "";
        char scan_symbol;
        char[] massivArab  = {'0', '1', '2', '3', '4', '5', '6', '7','8', '9'};
        char[] massivRim  = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};
        char[] massivOperation  = {'+', '-', '*', '/'};
        //System.out.println(dlina);
        for (int i = 0; i < vyrazhenie.length(); i++){
            scan_symbol = vyrazhenie.charAt(i);
            if (Contain(massivArab, scan_symbol)){
                if ((first_op != "" || second_op !="")&& !isArabNum){
                    throw new ArithmeticException("Начали с римских цифр и передумали?");
                }
                //exception
                if (operation == "") {
                    first_op += scan_symbol;
                }
                else second_op += scan_symbol;
            }
            else if (Contain(massivOperation, scan_symbol)) {
                if (operation == "")
                    operation += scan_symbol;
                else {
                    throw new ArithmeticException("Больше одной операции");
                }
            }
            else if (Contain(massivRim, scan_symbol)){
                if ((first_op != "" || second_op !="")&& isArabNum){
                    throw new ArithmeticException("Начали с арабских цифр и передумали?");
                }
                //exception
                isArabNum = false;
                if (operation == "") {
                    first_op += scan_symbol;
                }
                else second_op += scan_symbol;
            }


        }
        if (first_op == "" || operation =="" || second_op ==""){
            throw new ArithmeticException("Не хватает данных: операнда или операции");
        }
        if (isArabNum) {
            int firstOp = Integer.parseInt(first_op);
            int secondOp = Integer.parseInt(second_op);
            int resultat = 0;
            if ((firstOp <= 0 || firstOp > 10)||(secondOp <= 0 || secondOp > 10)) {
                throw new ArithmeticException("Калькулятор принимает на вход числа от 1 до 10 включительно");
            }
            switch (operation) {
                case "+":
                    resultat = firstOp + secondOp;
                    break;
                case "-":
                    resultat = firstOp - secondOp;
                    break;
                case "*":
                    resultat = firstOp * secondOp;
                    break;
                case "/":
                    resultat = firstOp / secondOp;
                    break;


            }

            System.out.println(resultat);

        }

        else {
            int firstOp = romanToDecimal(first_op);
            int secondOp = romanToDecimal(second_op);
            int resultat = 0;
            String resultatRoman = "";
            if ((firstOp <= 0 || firstOp > 10)||(secondOp <= 0 || secondOp > 10)) {
                throw new ArithmeticException("Калькулятор принимает на вход римские числа от I до X включительно");
            }
            switch (operation) {
                case "+":
                    resultat = firstOp + secondOp;
                    break;
                case "-":
                    resultat = firstOp - secondOp;
                    break;
                case "*":
                    resultat = firstOp * secondOp;
                    break;
                case "/":
                    resultat = firstOp / secondOp;
                    break;


            }
            if (resultat < 0){
                throw new ArithmeticException("В римской системе нет отрицательных чисел");
            }
            if (resultat == 0){
                throw new ArithmeticException("В римской системе нет нуля");
            }
            if (resultat > 0) {

                resultatRoman = decimalToRoman(resultat);
                System.out.println(resultatRoman);
            }
        }

    }
    //метод проверки на входимость
    public static boolean Contain(char[] args, char findElement){
        for (int i = 0; i < args.length; i++){
            if (args[i] == findElement)
                return true;
        }
        return false;
    }

    // roman to decimal
    public static int romanToDecimal(java.lang.String romanNumber) {
        int decimal = 0;
        int lastNumber = 0;
        String romanNumeral = romanNumber.toUpperCase();
        /* operation to be performed on upper cases even if user
           enters roman values in lower case chars */
        for (int x = romanNumeral.length() - 1; x >= 0 ; x--) {
            char convertToDecimal = romanNumeral.charAt(x);

            switch (convertToDecimal) {
                case 'M':
                    decimal = processDecimal(1000, lastNumber, decimal);
                    lastNumber = 1000;
                    break;

                case 'D':
                    decimal = processDecimal(500, lastNumber, decimal);
                    lastNumber = 500;
                    break;

                case 'C':
                    decimal = processDecimal(100, lastNumber, decimal);
                    lastNumber = 100;
                    break;

                case 'L':
                    decimal = processDecimal(50, lastNumber, decimal);
                    lastNumber = 50;
                    break;

                case 'X':
                    decimal = processDecimal(10, lastNumber, decimal);
                    lastNumber = 10;
                    break;

                case 'V':
                    decimal = processDecimal(5, lastNumber, decimal);
                    lastNumber = 5;
                    break;

                case 'I':
                    decimal = processDecimal(1, lastNumber, decimal);
                    lastNumber = 1;
                    break;
            }
        }
        return decimal;
    }

    public static int processDecimal(int decimal, int lastNumber, int lastDecimal) {
        if (lastNumber > decimal) {
            return lastDecimal - decimal;
        } else {
            return lastDecimal + decimal;
        }
    }

    // decimal to roman
    public static String decimalToRoman(int decimalNumber) {
        String roman = "";
        int decimal = decimalNumber;
        while (decimal >= 1000){
            roman += "M";
            decimal -= 1000;
        }
        while (decimal >= 900){
            roman += "CM";
            decimal -= 900;
        }
        while (decimal >= 500){
            roman += "D";
            decimal -= 500;
        }
        while (decimal >= 400){
            roman += "CD";
            decimal -= 400;
        }
        while (decimal >= 100){
            roman += "C";
            decimal -= 100;
        }
        while (decimal >= 90){
            roman += "XC";
            decimal -= 90;
        }
        while (decimal >= 50){
            roman += "L";
            decimal -= 50;
        }
        while (decimal >= 40){
            roman += "XL";
            decimal -= 40;
        }
        while (decimal >= 10){
            roman += "X";
            decimal -= 10;
        }
        while (decimal >= 9){
            roman += "IX";
            decimal -= 9;
        }
        while (decimal >= 5){
            roman += "V";
            decimal -= 5;
        }
        while (decimal >= 4){
            roman += "IV";
            decimal -= 4;
        }
        while (decimal >= 1){
            roman += "I";
            decimal -= 1;
        }
        return roman;
    }

}
