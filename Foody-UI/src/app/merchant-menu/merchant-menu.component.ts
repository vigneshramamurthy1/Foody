import { Component, OnInit } from '@angular/core';
import {cart, menu, Quantity} from "../menu/menu.component";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {MenuServiceService} from "../menu-service.service";
import { DomSanitizer } from '@angular/platform-browser';


@Component({
  selector: 'app-merchant-menu',
  templateUrl: './merchant-menu.component.html',
  styleUrls: ['./merchant-menu.component.css']
})
export class MerchantMenuComponent implements OnInit {

  model:menu[];

  modalCart:cart={
    quantity1:0,
    quantity2:0,
    quantity3:0
  };

  values:Quantity[] = [];

  constructor(private http:HttpClient, private router:Router,private menuService:MenuServiceService,
              public _DomSanitizationService: DomSanitizer ) { }

  ngOnInit() {
    if (sessionStorage.getItem("userData") == null) {
      this.router.navigate(['login']);
    }
    this.getItems();
  }

  clearLocal(){
    sessionStorage.clear();
  }

  getItems():void{
    this.menuService.getItems().subscribe((men: any[]) => {
      this.model = men;
      for (let i=0;i<this.model.length;i++){
        this.values.push(new Quantity());
        this.values[i].quantity=0;
      }
    });
  }

  getUpdate(value, id):void{
    let url = "http://localhost:8080/updateQuantity/"+id+"/"+value;
    this.http.get(url).subscribe(
      res=>{
        this.ngOnInit();
      },
      err=>{
        alert("Error adding items to cart");
      }
    )

  }
}
