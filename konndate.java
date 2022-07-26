import java.util.*;
import java.io.*;

class kondate {
//メインメソッド
  public static void main(String[] args){

    Control ctl = new Control();
    //ctl.menue = null;
    ctl.filecheck(ctl.menue);

    //System.out.println(ctl.history[0]);

    //==================
    //String menue[];
    //String history[] = new String[14];
/*
    FileReader fr = null;
    try{
      fr = new FileReader("menue.txt");
    }catch(IOException e){
      System.out.println("inout error");
    }catch(NumberFormatException e){
      System.out.println("NumberFormatException");
    }catch(Exception e){
      System.out.println("error");
    }
    Scanner scn = new Scanner(fr);
    int i = 0;
    while(scn.hasNext()){
      String s = scn.nextLine();
      System.out.println(s);
      ctl.menue[i] = s;
      i++;
    }
*/
    //==================

    int end = 0;
    //System.out.println("a");
    loop: for(end = 0; end == 0; ){
      System.out.println("\n1:献立候補一覧\n2:今日の献立入力\n3:献立履歴一覧\n4:新規メニュー追加\n99:終了\n");
      Scanner scan = new Scanner(System.in);
      try{
        int num = scan.nextInt();
        switch(num){
          case 1:
            System.out.println(num);
            ctl.fileall();
            break;
          case 2:
            System.out.println(num);
            ctl.daymenue();
            break;
          case 3:
            System.out.println(num);
            ctl.filehis();
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

  String menue[] = new String[28];
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
    i=0;
    while(i<14){
      history[i] = x[i];
      i++;
    }
  }

  void fileall(){
    System.out.println("========献立候補一覧========");
    for(int i = menue.length; i>0;i--){
      if(menue[i-1] != null){
        System.out.println(menue[i-1]);
      }
    }
    System.out.println("===========================");
  }

  void daymenue(){
    Scanner sc = new Scanner(System.in);
    System.out.println("今日の献立を入力してください");
    //String nms = sc.next();
    /*byte[] nm =  sc.next().getBytes( StandardCharsets.UTF_8 );
    System.out.println(nm);*/
    String newmenue = new String( sc.nextLine() , StandardCharsets.UFT_8 );

    //String newmenue = sc.next();
    System.out.println("テスト:"+newmenue);
    int delcheck = 0;
    int i;
    for(i = 14; i<menue.length; i++){
      if(newmenue == menue[i]){
        menue[i]=null;
        delcheck=1;
      }
    }
    if(delcheck == 0){
      for(i = menue.length; i >1;i--){
        if(menue[i-2] != null){
          menue[i-1]=menue[i-2];
        }
      }
      menue[0]=newmenue;
    }else{
      for(i=0; i < menue.length; i++){
        if(menue[i] == null){
          break;
        }
      }
      for(; i > 1; i-- ){
        if(menue[i-2] != null){
          menue[i-1]=menue[i-2];
        }
      }
      menue[0]=newmenue;
    }
    //change();
  }//daymenue end

  void filehis(){
    System.out.println("========献立履歴一覧========");
    for(int i = 0; i<history.length; i++){
      if(history[i] != null){
        System.out.println(history[i]);
      }
    }
    System.out.println("===========================");
  }

  void change(){
    //ファイルの書き込み
    String newtxt=menue[0];
    try{
      FileWriter fw = new FileWriter("menue.txt");
      PrintWriter pw = new PrintWriter(new BufferedWriter(fw));

      for(int i = 1;menue.length > i; i++ ){
        if(menue[i] != null){
          newtxt=(newtxt+"\n"+menue[i]);
        }
      }
      pw.println("生米");
      //System.out.println(newtxt);
      pw.close();
    }catch(IOException e){
      e.printStackTrace();
    }


  }//change end

}//操作クラス終了
