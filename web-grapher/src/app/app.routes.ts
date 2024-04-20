import { Routes } from '@angular/router';
import {StandardImagesTableComponent} from "./component/standard-images-table/standard-images-table.component";
import {HelpPageComponent} from "./component/help-page/help-page.component";
import {MappingComponent} from "./component/mapping/mapping.component";


export const routes: Routes = [
  {path: 'grapher', children: [
      {path: 'table', component: StandardImagesTableComponent},
      {path: 'mapping', component: MappingComponent},
      {path: 'help', component: HelpPageComponent},
    ]},
  {path: '', redirectTo: 'grapher/help', pathMatch: 'full'},
  {path: '**', redirectTo: 'grapher/help'}
];
