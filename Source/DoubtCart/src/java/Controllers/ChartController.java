/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Bean.AdminBeanLocal;
import Bean.DoubtBeanLocal;
import Bean.ResourceBeanLocal;
import Bean.UserBeanLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import org.primefaces.model.chart.DonutChartModel;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.optionconfig.animation.Animation;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.chart.PieChartModel;  
import org.primefaces.model.charts.donut.DonutChartDataSet;
import org.primefaces.model.charts.polar.PolarAreaChartModel;

/**
 *
 * @author HP
 */
@Named(value = "chartController")
@ApplicationScoped
public class ChartController implements Serializable {

    
    private PieChartModel pieModel;
    private PieChartModel pieModel2; 
    private BarChartModel barModel;
    private BarChartModel barModel2;
    @EJB ResourceBeanLocal rbl;
    @EJB UserBeanLocal ubl;
    @EJB DoubtBeanLocal dbl;
    @EJB AdminBeanLocal abl;

    Random random = new Random();

    HashMap<String, Integer> responses = new HashMap< String, Integer>();
    HashMap<String, Integer> responses2 = new HashMap< String, Integer>();

    
    @PostConstruct
    public void init() {
        System.out.println("Here in INIT");
        createBarModel();
        createBarModel2();
        createPieModel();
        createPieModel2();

    }
    /**
     * Creates a new instance of ChartController
     */
    public ChartController() {
    }
    
    public void refresh()
    {
         createBarModel();
        createBarModel2();
        createPieModel();
        createPieModel2();
    }
    
    public void createBarModel() {
        System.out.println("I'm in Bar CHart");
        barModel = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("");

        responses.put("Users",abl.AllUsers().size() );
        responses.put("Doubts", dbl.ListDoubts().size());
        responses.put("Resources", rbl.ListResources().size());
        
        
        List<Number> values = new ArrayList<>();
        for (HashMap.Entry r : responses.entrySet()) {
            values.add((Integer) r.getValue());
        }
        barDataSet.setData(values);

        List<String> bgColor = new ArrayList<>();
        bgColor.add("rgb(255, 99, 132)");
        bgColor.add("rgb(54, 162, 235)");
        bgColor.add("rgb(255, 205, 86)");
        barDataSet.setBackgroundColor(bgColor);

        List<String> borderColor = new ArrayList<>();
//        for (HashMap.Entry r : responses.entrySet()) {
//            bgColor.add("rgb(54, 162, 235)");
//        }
        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);

        data.addChartDataSet(barDataSet);

        List<String> labels = new ArrayList<>();
        for (HashMap.Entry r : responses.entrySet()) {
            labels.add(r.getKey().toString());
        }
        data.setLabels(labels);
        barModel.setData(data);

        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setText("Bar Chart");
        options.setTitle(title);

        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("bold");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(24);
        legend.setLabels(legendLabels);
        options.setLegend(legend);

        // disable animation
        Animation animation = new Animation();
        animation.setDuration(0);
        options.setAnimation(animation);

        barModel.setOptions(options);
    }
    
     public void createBarModel2() {
        System.out.println("I'm in Bar CHart");
        barModel2 = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("");

        responses2.put("Comments",abl.Allcomments().size() );
        responses2.put("Answers", abl.AllAnswers().size());       
        
        List<Number> values = new ArrayList<>();
        for (HashMap.Entry r : responses2.entrySet()) {
            values.add((Integer) r.getValue());
        }
        barDataSet.setData(values);

        List<String> bgColor = new ArrayList<>();
        bgColor.add("rgb(255, 99, 132)");
        bgColor.add("rgb(54, 162, 235)");
        bgColor.add("rgb(255, 205, 86)");
        barDataSet.setBackgroundColor(bgColor);

        List<String> borderColor = new ArrayList<>();
//        for (HashMap.Entry r : responses.entrySet()) {
//            bgColor.add("rgb(54, 162, 235)");
//        }
        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);

        data.addChartDataSet(barDataSet);

        List<String> labels = new ArrayList<>();
        for (HashMap.Entry r : responses2.entrySet()) {
            labels.add(r.getKey().toString());
        }
        data.setLabels(labels);
        barModel2.setData(data);

        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setText("Bar Chart");
        options.setTitle(title);

        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("bold");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(24);
        legend.setLabels(legendLabels);
        options.setLegend(legend);

        // disable animation
        Animation animation = new Animation();
        animation.setDuration(0);
        options.setAnimation(animation);

        barModel2.setOptions(options);
    }
      
    public void createPieModel2() {
        pieModel2 = new PieChartModel();

        pieModel2.set("Approved Answers", dbl.usefulAnswers().size());
        pieModel2.set("Not Approved Answers", dbl.NotusefulAnswers().size());
        pieModel2.setTitle("Pie Chart for Doubt Answer Ratio");
        pieModel2.setLegendPosition("c");
        pieModel2.setFill(true);
        pieModel2.setShowDataLabels(true);
        pieModel2.setDiameter(300);
        pieModel2.setShadow(false);

    }
      
    public void createPieModel() {
        pieModel = new PieChartModel();

        pieModel.set("Solved", dbl.SolvedDoubts().size());
        pieModel.set("Not Solved", dbl.UnsolvedDoubt().size());
        pieModel.setTitle("Pie Chart for Doubt Status");
        pieModel.setLegendPosition("c");
        pieModel.setFill(false);
        pieModel.setShowDataLabels(true);
        pieModel.setDiameter(300);
        pieModel.setShadow(false);
       
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

    public HashMap<String, Integer> getResponses() {
        return responses;
    }

    public void setResponses(HashMap<String, Integer> responses) {
        this.responses = responses;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public ResourceBeanLocal getRbl() {
        return rbl;
    }

    public void setRbl(ResourceBeanLocal rbl) {
        this.rbl = rbl;
    }

    public UserBeanLocal getUbl() {
        return ubl;
    }

    public void setUbl(UserBeanLocal ubl) {
        this.ubl = ubl;
    }

    public DoubtBeanLocal getDbl() {
        return dbl;
    }

    public void setDbl(DoubtBeanLocal dbl) {
        this.dbl = dbl;
    }

    public AdminBeanLocal getAbl() {
        return abl;
    }

    public void setAbl(AdminBeanLocal abl) {
        this.abl = abl;
    }

    public PieChartModel getPieModel2() {
        return pieModel2;
    }

    public void setPieModel2(PieChartModel pieModel2) {
        this.pieModel2 = pieModel2;
    }

    public BarChartModel getBarModel2() {
        return barModel2;
    }

    public void setBarModel2(BarChartModel barModel2) {
        this.barModel2 = barModel2;
    }

    public HashMap<String, Integer> getResponses2() {
        return responses2;
    }

    public void setResponses2(HashMap<String, Integer> responses2) {
        this.responses2 = responses2;
    }
    
      
      
      
      
}
