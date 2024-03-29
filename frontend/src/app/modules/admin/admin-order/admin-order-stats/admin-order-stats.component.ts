import { AfterViewInit, Component, ElementRef, OnInit, ViewChild } from '@angular/core';

import { AdminOrderService } from '../admin-order.service';
import { Chart, ChartData, registerables } from 'chart.js';


@Component({
  selector: 'app-admin-order-stats',
  templateUrl: './admin-order-stats.component.html',
  styleUrls: ['./admin-order-stats.component.scss']
})
export class AdminOrderStatsComponent implements  AfterViewInit {

  @ViewChild("stats") private stats!: ElementRef;
  chart!: Chart;


  ordersCount: number = 0;
  salesSum: number = 0;

  constructor(private adminOrderService: AdminOrderService)
  {
    Chart.register(...registerables);
  }
  private data = {
    labels: [],
    datasets: [
      {
        label: 'Zamówienia',
        data: [],
        borderColor: '#FF3F7C',
        backgroundColor: '#FF7A9F',
        order: 1,
        yAxisID: 'y'
      },
      {
        label: 'Sprzedaż',
        data: [],
        borderColor: '#0088FF',
        backgroundColor: '#00A1FF ',
        type: 'line',
        order: 0,
        yAxisID: 'y1'
      }
    ]
  } as ChartData;

  ngAfterViewInit(): void {
    this.setupChart();
    this.getSalesStatistics();
  }
  getSalesStatistics() {
    this.adminOrderService.getSalesStatistics().subscribe(
      stats => {
        this.data.labels =  stats.label;
        this.data.datasets[0].data = stats.order;
        this.data.datasets[1].data = stats.sale;
        this.chart.update();
        console.log(stats.order)
        console.log(stats.sale)
        this.ordersCount = stats.order.reduce((acc: number, value: number) => acc + value);
        this.salesSum = stats.sale.reduce((acc: number, value: number) => acc + value);
      }
    );
  }
  setupChart() {
    this.chart = new Chart(this.stats.nativeElement, 
      {
        type: 'bar',
        data: this.data,
        options: {
        responsive: true,
        plugins: {
          legend: {
            position: 'top',
          },
          title: {
            display: true,
            text: 'Wykres sprzedaży'
          }
        },
        scales: {
          y: {
            type: 'linear',
            display: true,
            position: 'left',
          },
          y1: {
            type: 'linear',
            display: true,
            position: 'right',
                // grid line settings
            grid: {
              drawOnChartArea: false, // only want the grid lines for one axis to show up
            },
          }
        }
      }
    } )
  }


}
