package com.example.login_testt;

public class Library {

    String Book_Name;
    String Author_Name;

    String Child_Name;

    boolean Check_In_Out;


    public Library(){

    }
    public Library(String editBook_Name, String editAuthorName) {
        this.Book_Name = editBook_Name;
        this.Author_Name = editAuthorName;
        Child_Name = "";
        Check_In_Out = false;
    }

    //Need to write constructurer for Child name when adding

    public boolean isCheck_In_Out() {
        return Check_In_Out;
    }

    public void setCheck_In_Out(boolean check_In_Out) {
        Check_In_Out = check_In_Out;
    }

    public String getBook_Name() {
        return Book_Name;
    }

    public void setBook_Name(String book_Name) {
        Book_Name = book_Name;
    }

    public String getAuthor_Name() {
        return Author_Name;
    }

    public void setAuthor_Name(String author_Name) {
        Author_Name = author_Name;
    }

    public String getChild_Name() {
        return Child_Name;
    }

    public void setChild_Name(String child_Name) {
        Child_Name = child_Name;
    }
}





