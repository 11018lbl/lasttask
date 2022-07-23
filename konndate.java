import java.util.*;
import java.io.*;

class kondate {
//メインメソッド
  public static void main(String[] args){
    /*Control ctl;
    ctl = new Control();
    //ctl.menue = null;
    ctl.filecheck(ctl.menue);
    System.out.println(ctl.menue);*/
    //System.out.println(ctl.history);

    //==================
    String menue[] = null;
    String history[] = new String[14];

    FileReader fr = null;
    try{
      fr = new FileReader("menue.txt");
      Scanner scn = new Scanner(fr);
      int i = 0;
      while(scn.hasNext()){
        String s = scn.nextLine();
        menue[i] = s;
        i++;
      }
    }catch(IOException e){
      System.out.println("inout error");
    }catch(NumberFormatException e){
      System.out.println("NumberFormatException");
    }catch(Exception e){
      System.out.println("error");
    }

    //==================

    int end = 0;
    //System.out.println("a");
    loop: for(end = 0; end == 0; ){
      System.out.println("1:献立候補一覧\n2:献立入力\n3:献立履歴一覧\n4:\n99:終了\n");
      Scanner scan = new Scanner(System.in);
      try{
        int num = scan.nextInt();
        switch(num){
          case 1:
            System.out.println(num);
            break;
          case 2:
            System.out.println(num);
            break;
          case 3:
            System.out.println(num);
            break;
          case 4:
            System.out.println(num);
            break;
          case 99:
            end = 1;
            System.out.println("終了します。");
            break;
          default:
            System.out.println("指定した値を入力してください");
            break;
          }
        }catch(InputMismatchException e){
          System.out.println("数値を入力してください");
        }

        //System.out.println("b");

    }
  //System.out.println("c");

  }//メインメソッド終了


}

//操作クラス
class Control{

  String menue[];
  String history[] = new String[14];

  void filecheck(String[] x){
    FileReader fr = null;
    try{
      fr = new FileReader("menue.txt");
    }catch(Exception e){
      System.out.println("error");
    }
    Scanner scn = new Scanner(fr);
    int i = 0;
    while(scn.hasNext()){
      String s = scn.nextLine();
      x[i] = s;
      i++;
    }
    /*i=0;
    while(i<14){
      history[i] = menue[i];
      i++;
    }*/
  }

  void fileall(){

  }
}//操作クラス終了
