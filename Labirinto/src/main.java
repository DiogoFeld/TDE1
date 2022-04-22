import java.util.Arrays;

public class main {

    //array bidimensional "acho"

    static String[][] labyrinth = {{}};
    static int[][] entrance;
    static int[][] stack = {{}};
    static boolean dungeonRun = true;

    static int steps = 0;
    static int top =0;
    static int vertical = 0;
    static int horizontal = 0;

    public static void main(String[] args) {
        labyrinth = sample1;
        dungeonCrawl(labyrinth);
        clearStack();
        resetCrawl();
        System.out.println();
        System.out.println("-----------xxxxxxxxxxxxxxxxxxxx-----------");
        labyrinth = sample2;
        dungeonCrawl(labyrinth);
    }

    static private void PrintLabirinty(String[][] labirintySample) {
        for (int l = 0; l < labirintySample.length; l++) {
            System.out.println();
            for (int c = 0; c < labirintySample[l].length; c++) {
                System.out.print(labirintySample[l][c]);
            }
        }
    }

    static private int[] findEntrance(String[][] labirintySample) {
        int[] position;
        for (int l = 0; l < labirintySample.length; l++) {
            System.out.println();
            for (int c = 0; c < labirintySample[l].length; c++) {
                if (labirintySample[l][c] == "E") {
                    position = new int[]{l, c};
                    return position;
                }
            }
        }
        position = new int[]{0, 0};
        return position;
    }

    static private String[][] dungeonCrawl(String[][] labirintySample) {
        entrance = new int[][]{findEntrance(labirintySample)};
        horizontal = entrance[0][0];
        vertical = entrance[0][1];

        PrintLabirinty(labirintySample);
        crawl();
        printStack();

        return labirintySample;
    }


    public static void crawl() {
        while (dungeonRun) {
            crawlUp(vertical, horizontal);
            steps++;
        }
    }

    public static void crawlUp(int y, int x) {
        int formerX = x;
        int formerY = y;

        int[] position = new int[0];
        if (y != 10 || y != 0) {
            y = y -1;

            if (labyrinth[y][x] == " ") {
                position = new int[]{y, x};
                vertical = y;
                horizontal = x;
                changeFormerPosition(formerY,formerX);
            }
            else if(labyrinth[y][x] == "S"){
                position = new int[]{y, x};
                vertical = y;
                horizontal = x;
                changeFormerPosition(formerY,formerX);
                FinishCrawl();
            }
            else {
                crawlRight(formerY, formerX, formerX, formerY);
            }
        } else {
            crawlRight(formerY, formerX, formerX, formerY);
        }
    }

    public static void crawlRight(int y, int x, int formerX, int formerY) {
        int[] position = new int[0];

        if (x != 10 || x != 0) {
            x = x + 1;
            if (labyrinth[y][x] == " ") {
                position = new int[]{y, x};
                vertical = y;
                horizontal = x;
                changeFormerPosition(formerY,formerX);
            }
            else if(labyrinth[y][x] == "S"){
                position = new int[]{y, x};
                vertical = y;
                horizontal = x;
                changeFormerPosition(formerY,formerX);
                FinishCrawl();
            }
            else {
                crawlDown(formerY, formerX, formerX, formerY);
            }
        } else {
            crawlDown(formerY, formerX, formerX, formerY);
        }
    }

    public static void crawlDown(int y, int x, int formerX, int formerY) {
        int[] position = new int[0];


        if (y != 10 || y != 0) {
            y = y + 1;
            if (labyrinth[y][x] == " ") {
                position = new int[]{y, x};
                vertical = y;
                horizontal = x;
                changeFormerPosition(formerY,formerX);
            }
            else if(labyrinth[y][x] == "S"){
                position = new int[]{y, x};
                vertical = y;
                horizontal = x;
                changeFormerPosition(formerY,formerX);
                FinishCrawl();
            }
            else {
                crawlLeft(formerY,formerX, formerX, formerY);
            }
        } else {
            crawlLeft(formerY,formerX, formerX, formerY);
        }
    }

    public static void crawlLeft(int y, int x, int formerX, int formerY) {
        int[] position = new int[0];

        if (x != 10 || x != 0) {
            x = x - 1;
            if (labyrinth[y][x] == " ") {
                position = new int[]{y, x};
                vertical = y;
                horizontal = x;
                changeFormerPosition(formerY,formerX);
            }
            else if(labyrinth[y][x] == "S"){
                position = new int[]{y, x};
                vertical = y;
                horizontal = x;
                changeFormerPosition(formerY,formerX);
                FinishCrawl();
            }
            else {
                getBack(formerY,formerX);
            }
        } else {
            getBack(formerY,formerX);
        }
    }


    private static void getBack(int y, int x) {
        labyrinth[y][x] = "X";

        turnBack();
    }

    private static void FinishCrawl(){
        int[] position = new int[]{vertical,horizontal};
        push(position);

        dungeonRun = false;
    }


    private static void resetCrawl(){
        dungeonRun = true;
        steps = 0;
        top = 0;
    }

    private static void changeFormerPosition(int y, int x) {
        labyrinth[y][x] = "X";
        int[] position = new int[]{y,x};
        push(position);

        PrintLabirinty(labyrinth);
        System.out.println("");
    }

    //arrays
    static String[][] sample1 = {
            {"#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#"},//0
            {"#", "E", "#", "#", "S", "#", "#", "#", "#", "#", "#"},//1
            {"#", " ", "#", "#", " ", "#", "#", "#", "#", "#", "#"},//2
            {"#", " ", "#", "#", " ", " ", " ", " ", " ", "#", "#"},//3
            {"#", " ", "#", "#", "#", "#", "#", "#", " ", "#", "#"},//4
            {"#", " ", " ", " ", " ", " ", "#", "#", " ", "#", "#"},//6
            {"#", "#", "#", "#", "#", " ", "#", "#", " ", "#", "#"},//5
            {"#", " ", " ", " ", " ", " ", "#", "#", " ", "#", "#"},//7
            {"#", " ", "#", "#", "#", "#", "#", "#", " ", "#", "#"},//8
            {"#", " ", " ", " ", " ", " ", " ", " ", " ", "#", "#"},//9
            {"#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#"}};//10

    static String[][] sample2 = {
            {"#", "#","#","#","#","#","#","#","#","#"},//0
            {"#", "#","E","#","#"," "," "," "," ","#"},//1
            {"#", "#"," ","#","#"," ","#","#"," ","#"},//2
            {"#", "#"," ","#","#","S","#","#"," ","#"},//3
            {"#", "#"," ","#"," ","#"," "," "," ","#"},//4
            {"#", "#"," ","#"," ","#","#","#"," ","#"},//6
            {"#", " "," "," "," "," "," ","#"," ","#"},//5
            {"#", " ","#","#","#","#"," ","#"," ","#"},//7
            {"#", " ","#","#","#","#"," ","#"," ","#"},//8
            {"#", " ","#","#","#","#"," "," "," ","#"},//9
            {"#", "#","#","#","#","#","#","#","#","#"}};//10

    //array part
    public static void push(int[] element) {
        if(top >= getCapacity() - 1) {
            int newCapacity = getCapacity() + 1;
            var newArray = Arrays.copyOf(stack, newCapacity);
            stack = newArray;
        }
        top = top + 1;
        stack[top] = element;
    }


    private static void clearStack() {
        stack = new int[][]{{}};
    }

    public static int getCapacity() {
        return stack.length;
    }

    public static void printStack() {
        for (int l = 0; l < stack.length; l++) {
            System.out.println();
            for (int c = 0; c < stack[l].length; c++) {
                System.out.print(stack[l][c]);
                System.out.print(" ");
            }
        }
    }

    public static void turnBack(){
        top--;

        if(top < 0){
            giveUp();
        }

        vertical = stack[top][0];
        horizontal = stack[top][1];
    }

    public static void giveUp(){
        System.out.println("Não a escapatória, desista da crawl.");
        dungeonRun = false;
    }



}
