package com.carpg.util;

import java.awt.Color;
import java.awt.Font;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

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

//��Ҫ����Ϊjfreechart��һ����������д���
public class Chart {
	
	
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

	//��������ͼ����Դ
	public static XYDataset createXYDataset() {
        TimeSeries timeseries = new TimeSeries("�²�������",
                org.jfree.data.time.Year.class);
        timeseries.add(new Year(2008), 10.1D);
        timeseries.add(new Year(2009), 15.1D);
        timeseries.add(new Year(2010), 12.1D);
        timeseries.add(new Year(2011), 14.1D);
        timeseries.add(new Year(2012), 13.1D);
        /*
        timeseries.add(new Month(2, 2013), 10.1D);
        timeseries.add(new Month(3, 2013), 12.3D);
        timeseries.add(new Month(4, 2013), 13.2D);
        timeseries.add(new Month(5, 2013), 14.2D);
        timeseries.add(new Month(6, 2013), 16.8D);
        timeseries.add(new Month(7, 2013), 20.7D);
        timeseries.add(new Month(8, 2013), 28.4D);
        timeseries.add(new Month(9, 2013), 30.3D);
        timeseries.add(new Month(10, 2013), 32.2D);
        timeseries.add(new Month(11, 2013), 35.4D);
        timeseries.add(new Month(12, 2013), 40.0D);
        timeseries.add(new Month(1, 2014), 38.4D);
        timeseries.add(new Month(2, 2014), 36.5D);
        timeseries.add(new Month(3, 2014), 34.0D);
        timeseries.add(new Month(4, 2014), 35.0D);
        timeseries.add(new Month(5, 2014), 36.1D);
        timeseries.add(new Month(6, 2014), 35.0D);
        timeseries.add(new Month(7, 2014), 34.5D); */
        /*
        TimeSeries timeseries1 = new TimeSeries("L&G UK Index Trust",
                org.jfree.data.time.Month.class);
        timeseries1.add(new Month(2, 2001), 129.59999999999999D);
        timeseries1.add(new Month(3, 2001), 123.2D);
        timeseries1.add(new Month(4, 2001), 117.2D);
        timeseries1.add(new Month(5, 2001), 124.09999999999999D);
        timeseries1.add(new Month(6, 2001), 122.59999999999999D);
        timeseries1.add(new Month(7, 2001), 119.2D);
        timeseries1.add(new Month(8, 2001), 116.5D);
        timeseries1.add(new Month(9, 2001), 112.7D);
        timeseries1.add(new Month(10, 2001), 101.5D);
        timeseries1.add(new Month(11, 2001), 106.09999999999999D);
        timeseries1.add(new Month(12, 2001), 110.3D);
        timeseries1.add(new Month(1, 2002), 111.7D);
        timeseries1.add(new Month(2, 2002), 111D);
        timeseries1.add(new Month(3, 2002), 109.59999999999999D);
        timeseries1.add(new Month(4, 2002), 113.2D);
        timeseries1.add(new Month(5, 2002), 111.59999999999999D);
        timeseries1.add(new Month(6, 2002), 108.8D);
        timeseries1.add(new Month(7, 2002), 101.59999999999999D);
        */
        TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
        timeseriescollection.addSeries(timeseries);
        //timeseriescollection.addSeries(timeseries1);
        
        return timeseriescollection;
    }
	//��������ͼchart
	public static JFreeChart createXYChart() {
        JFreeChart jfreechart = ChartFactory.createTimeSeriesChart(
                "����Ʒ������ͳ��", "����", "�²���",
                Chart.createXYDataset(), true, true, true);
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
	
	//������״ͼ����Դ
	public static DefaultPieDataset createPeiDataset(){
		//���ñ�ͼ���ݼ�  
		DefaultPieDataset dataset = new DefaultPieDataset();  
		dataset.setValue("��Ժ��", 720);  
		dataset.setValue("���԰�", 530);  
		dataset.setValue("��Ժ�˼�", 210);  
		dataset.setValue("�а�", 91);  
		dataset.setValue("����", 66);
		
		return dataset;
	}
	//������״ͼ��jfreechart
	public static JFreeChart createPeiChart(){
		//ͨ������������JFreeChart����  
		JFreeChart chart = ChartFactory.createPieChart3D("�̻�����ע��ͳ��", Chart.createPeiDataset(), true, true, false);  
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
