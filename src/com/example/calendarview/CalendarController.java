package com.example.calendarview;

import entities.Evenement;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import services.EvenementService;

public class CalendarController implements Initializable {

    @FXML
    private FlowPane calendar;
    @FXML
    private Text year;
    @FXML
    private Text month;

    LocalDate dateFocus;
    LocalDate today;

    List<Evenement> evenements;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateFocus = LocalDate.now();
        today = LocalDate.now();
        evenements = new EvenementService().getAll(); // create a new object and call the method
        
        drawCalendar();
    }

    @FXML
    void backOneMonth(ActionEvent event) {
        dateFocus = dateFocus.minusMonths(1);
        calendar.getChildren().clear();
        drawCalendar();
    }

    @FXML
    void forwardOneMonth(ActionEvent event) {
        dateFocus = dateFocus.plusMonths(1);
        calendar.getChildren().clear();
        drawCalendar();
    }

    private void drawCalendar(){
        year.setText(String.valueOf(dateFocus.getYear()));
        month.setText(dateFocus.getMonth().toString());

        double calendarWidth = calendar.getPrefWidth();
        double calendarHeight = calendar.getPrefHeight();
        double strokeWidth = 1;
        double spacingH = calendar.getHgap();
        double spacingV = calendar.getVgap();

        // Map of events for a given month
        Map<Integer, List<Evenement>> eventsMap = getEventsMonth(dateFocus);

        int monthMaxDate = dateFocus.getMonth().maxLength();
        // Check for leap year
        if(dateFocus.getYear() % 4 != 0 && monthMaxDate == 29){
            monthMaxDate = 28;
        }
        int dateOffset = LocalDate.of(dateFocus.getYear(), dateFocus.getMonth(), 1).getDayOfWeek().getValue();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                StackPane stackPane = new StackPane();

                Rectangle rectangle = new Rectangle();
                rectangle.setFill(Color.TRANSPARENT);
                rectangle.setStroke(Color.BLACK);
                rectangle.setStrokeWidth(strokeWidth);
                double rectangleWidth =(calendarWidth/7) - strokeWidth - spacingH;
                rectangle.setWidth(rectangleWidth);
                double rectangleHeight = (calendarHeight/6) - strokeWidth - spacingV;
                rectangle.setHeight(rectangleHeight);
                stackPane.getChildren().add(rectangle);

                int calculatedDate = (j+1)+(7*i);
               
                
                if (calculatedDate > dateOffset) {
    int currentDate = calculatedDate - dateOffset;
    if (currentDate <= monthMaxDate) {
        Text date = new Text(String.valueOf(currentDate));
        double textTranslationY = - (rectangleHeight / 2) * 0.75;
        date.setTranslateY(textTranslationY);
        stackPane.getChildren().add(date);
    }
    List<Evenement> events = eventsMap.get(currentDate);
    if (events != null) {
        
        createEventsBox(events, rectangleHeight, rectangleWidth, stackPane);
    }
    if (today.getYear() == dateFocus.getYear() && today.getMonth() == dateFocus.getMonth() && today.getDayOfMonth() == currentDate) {
        rectangle.setFill(Color.LIGHTBLUE);
    }


            calendar.getChildren().add(stackPane);
        }
            }}
    }


private void createEventsBox(List<Evenement> events, double rectangleHeight, double rectangleWidth, StackPane stackPane){
    VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER); // set the alignment to CENTER

    vBox.setSpacing(rectangleHeight/10);
    

    for(Evenement event : events){
        Text eventText = new Text(event.getNom_ev());
        eventText.setWrappingWidth(rectangleWidth);
        vBox.getChildren().add(eventText);
    }
    
    Text dateText = new Text(String.valueOf(events.get(0).getDated_ev().getDayOfMonth()));
    dateText.setFill(Color.BLACK);
    dateText.setStyle("-fx-font-weight: bold;");
    vBox.getChildren().add(0, dateText);

    vBox.setStyle("-fx-background-color: pink; -fx-border-color: black; -fx-border-width: 1px;");

    stackPane.getChildren().add(vBox);

   
}

private Map<Integer, List<Evenement>> getEventsMonth(LocalDate date){
    Map<Integer, List<Evenement>> eventsMap = new HashMap<>();
    for(Evenement event : evenements){
        LocalDate eventDate = event.getDated_ev();
        if(eventDate.getYear() == date.getYear() && eventDate.getMonth() == date.getMonth()){
            List<Evenement> eventsList = eventsMap.get(eventDate.getDayOfMonth());
            if(eventsList == null){
                eventsList = new ArrayList<>();
            }
            eventsList.add(event);
            eventsMap.put(eventDate.getDayOfMonth(), eventsList);
        }
    }

    return eventsMap;
}

    
}
