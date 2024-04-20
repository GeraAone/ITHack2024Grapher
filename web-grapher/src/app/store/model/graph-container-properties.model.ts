import {AbstractPropertiesModel} from "./abstract-properties.model";


export class GraphContainerPropertiesModel extends AbstractPropertiesModel {
  constructor(public widthPx: number,
              public heightPx: number) {
    super();
    this.widthPx = widthPx;
    this.heightPx = heightPx;
  }
}
