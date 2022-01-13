import { Component, ElementRef, Input, OnInit, ViewChild } from '@angular/core';
import { HTMLWorker, jsPDF } from 'jspdf';
import { Teams } from '../datamodel/Teams';

@Component({
  selector: 'app-certificate',
  templateUrl: './certificate.component.html',
  styleUrls: ['./certificate.component.css']
})
export class CertificateComponent implements OnInit {

  constructor() { }


  @Input() public questionId: any = 1;

  @Input() public team: any = Teams.LMNR;

  @Input() public name: string = 'Nithin Prasad';

  @Input() public award: string = 'MOVER OF MOUNTAINS';

  @Input() public description: string = 'The one who is capable of achieving the toughest of the tough targets with his/her hardwork and dedication.';


  @ViewChild('pdfTable') pdfTable: any;

  ngOnInit(): void {
  }

  createPdf(id: any): void {
    if (document == null || document.getElementById(id) == null) {
      return;
    }
    if (document.getElementById(id) == null) {
      return;
    }
    let printContents = document.getElementById(id);
    let doc = new jsPDF('l', 'pt');
    let element: any = document.getElementById(id);

    // var img = new Image();
    //img.src = '../../assets/images/certificate.png';

    var logo_url = "../../assets/images/certificate.png";
    // doc=doc.addImage(img, 'png', 100, 780, 120, 150);
    let worked: HTMLWorker = doc.html(
      element, {
      html2canvas: {
        width: 200,
      }
    }
    );

    worked.save(this.award);
  }

}
