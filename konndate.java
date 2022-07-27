import java.util.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class kondate {
//メインメソッド
  public static void main(String[] args){

    Control ctl = new Control();
    ctl.filecheck(ctl.menue);

    int end = 0;
    loop: for(end = 0; end == 0; ){
      System.out.println("\n1:献立候補一覧\n2:今日の献立入力\n3:献立履歴一覧\n4:新規メニュー追加\n5:登録名検索\n99:終了\n");
      Scanner scan = new Scanner(System.in);
      try{
        int num = scan.nextInt();
        switch(num){
          case 1:
            ctl.fileall();
            break;
          case 2:
            ctl.daymenue();
            break;
          case 3:
            ctl.filehis();
            break;
          case 4:
            ctl.nextmenue();
            break;
          case 5:
            System.out.println("検索方法を選択してください\n1:前方一致\n2:後方一致\n3:曖昧検索");
            try{
              num = scan.nextInt();
              if((num==1) || (num==2) || (num==3)) {ctl.namecheck(num);
              }else{System.out.println("不正な数字です。");}
            }catch(Exception e){System.out.println("error1");}
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
    }
  }//メインメソッド終了


}

//操作クラス
class Control{

  String menue[] = new String[28];
  String history[] = new String[14];
  Pattern rim1 = Pattern.compile("^[0-9a-zA-Z\\-]+$");

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
    System.out.println("\n========献立候補一覧========");
    for(int i = menue.length; i>0;i--){
      if(menue[i-1] != null){
        System.out.println(menue[i-1]);
      }
    }
    System.out.println("===========================");
  }

  void daymenue(){
    Scanner sc = new Scanner(System.in);
    int i;
    for(;;){
      System.out.println("今日の献立を入力してください");
      try{
      String newmenue = sc.next();
      Matcher m = rim1.matcher(newmenue);
        if(m.find()){

          int delcheck = 0;
          for(i = 14; i<menue.length; i++){
            if(newmenue.equals(menue[i])){
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
          change();
          break;
        }
        System.out.println("\nエラー:使用できない文字が含まれています。");
        System.out.println("====== ＊使用可能文字：半角英数,- ======\n");
      }catch(Exception e){}
    }
  }//daymenue end

  void filehis(){
    System.out.println("\n========献立履歴一覧========");
    for(int i = 0; i<history.length; i++){
      if(history[i] != null){
        System.out.println(history[i]);
      }
    }
    System.out.println("===========================");
  }

  void nextmenue(){
    Scanner scanner = new Scanner(System.in);
    for(;;){
    System.out.println("新しいメニューを入力してください");
    try{
    String nxmenue = scanner.nextLine();
    Matcher m = rim1.matcher(nxmenue);
    if(m.find()){

    int i;
    for(i = 0; i < menue.length ; i++){
      if(menue[i]==null){
        menue[i]=nxmenue;
        break;
      }
    }
    if(i>menue.length){
      menue[i-1]=nxmenue;
    }
    change();

    break;
    }
    System.out.println("\nエラー:使用できない文字が含まれています。");
    System.out.println("====== ＊使用可能文字：半角英数,- ======\n");
    }catch(Exception e){}
    }

  }//nextmenue end

  void namecheck(int x){
    Scanner sc = new Scanner(System.in);
    for(;;){
    System.out.println("検索する名前を入力してください");
    try{
    String name = sc.nextLine();
    Matcher ma = rim1.matcher(name);
      if(ma.find()){

    String[] checklist = new String[28];
    int i;
    int l = 0;
    switch(x){
        case 1:
          for(i = 0; i<menue.length;i++){
            if(menue[i] != null){
              if(menue[i].startsWith(name)){
                checklist[l]=menue[i];
                l++;
                System.out.println(l);
              }
            }
          }
          break;

        case 2:
          for(i = 0; i<menue.length;i++){
            if(menue[i] != null){
              if(menue[i].endsWith(name)){
                checklist[l]=menue[i];
                l++;
              }
            }
          }
          break;

        case 3:
          double[] score = new double[menue.length];
          String[] key = name.split("");
          for(i=0; i<key.length-1; i++){
            for(l=i+1;l<key.length;l++){
              if(key[i]!=null && key[l]!=null){
                if(key[i].equals(key[l])){
                  key[l]=null;
                }
              }
            }
          }
          String tester;
          for(l=0; l<menue.length; l++){
            score[l]=0;
            for(i=0;i<key.length;i++){
              if(key[i]!=null && menue[l] != null){
                String tesp = "^.*"+key[i]+".*$";
                Pattern pattern = Pattern.compile(tesp);
                tester = menue[l];
                Matcher m = pattern.matcher(tester);
                if(m.find()){
                  score[l]=score[l]+1;
                }
              }
            }
          }
          double keylong = (double)(i);
          l=0;
          for(i=0;i<menue.length;i++){
            score[i] = score[i] / keylong;
            if(score[i] > 0.5){
              checklist[l]=menue[i];
              l++;
            }
          }
          break;

        default:
          break;
    }
    System.out.println("========検索結果========");
    for(i=0;i<checklist.length;i++){
      if(checklist[i] != null){
        System.out.println(checklist[i]);
      }else{break;}
    }
    System.out.println("========================");

    break;
    }
    System.out.println("\nエラー:使用できない文字が含まれています。");
    System.out.println("====== ＊使用可能文字：半角英数,- ======\n");
    }catch(Exception e){}

    }
  }//namecheck end

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
      pw.println(newtxt);
      pw.close();
    }catch(IOException e){
      e.printStackTrace();
    }
  }//change end

}//操作クラス終了
