package com.carpg.util;

import java.awt.Color;
import java.awt.Font;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.TextAnchor;
import org.jfree.util.Rotation;

import com.carpg.dao.StatisticDao;
import com.carpg.impl.StatisticImpl;

//��Ҫ����Ϊjfreechart��һ����������д���
public class Chart {
	
	private StatisticDao statistic = new StatisticImpl();
	
	
	//����������ʽ����Ҫ�ǽ��������������
	public static StandardChartTheme createTheme(){
		//����������ʽ  
		 StandardChartTheme standardChartTheme=new StandardChartTheme("CN");
		//���ñ�������  
		 standardChartTheme.setExtraLargeFont(new Font("����",Font.BOLD,20));  
		//����ͼ��������  
		 standardChartTheme.setRegularFont(new Font("����",Font.PLAIN,15));  
		//�������������  
		 standardChartTheme.setLargeFont(new Font("����",Font.PLAIN,15));
		 
		 return standardChartTheme;
	}

	//������Ʒ��-���ͳ������ͼ����Դ
	private XYDataset createXYDataset(String brand) {
        TimeSeries timeseries = new TimeSeries("����״������",
                org.jfree.data.time.Year.class);
        
        Map<String, Integer> map = statistic.getCountByYear_brand(brand);
        //�õ���ǰ�����
        Calendar c = Calendar.getInstance();
        int currentYear = c.getTime().getYear();
        //չʾ����ȥ5�������״��
        for (int k=currentYear; k>= currentYear-4; k--){
        	//��ʱȷ����λΪ��������
        	double value = map.get(String.valueOf(k)) / 100;
        	timeseries.add(new Year(k), value);
        }
        TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
        timeseriescollection.addSeries(timeseries);
        //timeseriescollection.addSeries(timeseries1);
        
        return timeseriescollection;
    }
	//������Ʒ��-�������ͼchart
	public JFreeChart createXYChart(String brand) {
        JFreeChart jfreechart = ChartFactory.createTimeSeriesChart(
                "����Ʒ������ͳ��", "���", "�²���",
                createXYDataset(brand), true, true, true);
        jfreechart.setBackgroundPaint(Color.white);
        XYPlot xyplot = (XYPlot) jfreechart.getPlot();
        xyplot.setBackgroundPaint(Color.lightGray);
        xyplot.setDomainGridlinePaint(Color.white);
        xyplot.setRangeGridlinePaint(Color.white);
        xyplot.setAxisOffset(new RectangleInsets(5D, 5D, 5D, 5D));
        xyplot.setDomainCrosshairVisible(true);
        xyplot.setRangeCrosshairVisible(true);
        org.jfree.chart.renderer.xy.XYItemRenderer xyitemrenderer = xyplot
                .getRenderer();
        if (xyitemrenderer instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer) xyitemrenderer;
            xylineandshaperenderer.setBaseShapesVisible(true);
            xylineandshaperenderer.setBaseShapesFilled(true);
         // ��ʾ�ڵ��ֵ  
            xylineandshaperenderer.setBaseItemLabelsVisible(true);  
            xylineandshaperenderer  
                    .setBasePositiveItemLabelPosition(new ItemLabelPosition(  
                            ItemLabelAnchor.OUTSIDE7, TextAnchor.BASELINE_CENTER));  
            xylineandshaperenderer  
                    .setBaseItemLabelGenerator(new StandardXYItemLabelGenerator());
        }
        DateAxis dateaxis = (DateAxis) xyplot.getDomainAxis();
        dateaxis.setDateFormatOverride(new SimpleDateFormat("����yyyy����"));
        return jfreechart;
    }
	
	//������һ��ݸ���Ʒ�Ƶ�����״��ͳ�Ʊ�״ͼ����Դ
	private DefaultPieDataset createPeiDataset(String year){
		//���ñ�ͼ���ݼ�  
		DefaultPieDataset dataset = new DefaultPieDataset();
		//�õ�ͳ����
		LinkedHashMap<String, Integer> map = (LinkedHashMap<String, Integer>) statistic.getCountByYear(year);
		Set set = map.entrySet();
		Iterator iterator = set.iterator();
		//����hashMap�����뵽����Դ
		while (iterator.hasNext()){
			 Map.Entry element = (Map.Entry)iterator.next(); 
	         String  key = (String)element.getKey(); 
	         int  value = (Integer)element.getValue();
	         dataset.setValue(key, value);
		}
		
		return dataset;
	}
	//������״ͼ��jfreechart
	public JFreeChart createPeiChart(String year){
		//ͨ������������JFreeChart����  
		JFreeChart chart = ChartFactory.createPieChart3D("�̻�����ע��ͳ��", createPeiDataset(year), true, true, false);  
		chart.addSubtitle(new TextTitle("2014�ϰ���"));  
		PiePlot pieplot = (PiePlot) chart.getPlot();  
		pieplot.setLabelFont(new Font("����", 0, 11));  
		StandardPieSectionLabelGenerator standarPieIG = new StandardPieSectionLabelGenerator("{0}:({1},{2})", NumberFormat.getNumberInstance(), NumberFormat.getPercentInstance());  
		pieplot.setLabelGenerator(standarPieIG);  
		  
		//û�����ݵ�ʱ����ʾ������  
		pieplot.setNoDataMessage("��������ʾ");  
		pieplot.setLabelGap(0.02D);
		
		//��һ��������key,�ڶ���������ͻ����ʾ�Ĵ�С�������Լ�����һ�¿���Ч���������ˣ�   
		//pieplot.setExplodePercent("�ǹ�ǿ��",0.23);
		  
		PiePlot3D pieplot3d = (PiePlot3D)chart.getPlot();  
		//���ÿ�ʼ�Ƕ�  
		pieplot3d.setStartAngle(120D);  
		//���÷���Ϊ��˳ʱ�뷽��  
		pieplot3d.setDirection(Rotation.CLOCKWISE);  
		//����͸���ȣ�0.5FΪ��͸����1Ϊ��͸����0Ϊȫ͸��  
		pieplot3d.setForegroundAlpha(0.7F); 
		
		return chart;
	}
}
