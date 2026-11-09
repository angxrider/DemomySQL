module edu.utj.poo.mysql.demomysql {
    requires javafx.controls;
    requires javafx.fxml;
    requires mysql.connector.j;
    requires java.sql;

    opens edu.utj.poo.mysql.demomysql to javafx.fxml, javafx.base;
    opens edu.utj.poo.mysql.demomysql.controladores to javafx.fxml;
    opens edu.utj.poo.mysql.demomysql.daos to javafx.fxml;

    exports edu.utj.poo.mysql.demomysql;
    exports edu.utj.poo.mysql.demomysql.controladores;
}
