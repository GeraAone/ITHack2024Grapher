import {AbstractPropertiesModel} from "./abstract-properties.model";


/**
 * Identically equals a line model in context graphs (but of course, link is not necessary equal line).
 */
export class LinkPropertiesModel extends AbstractPropertiesModel {
  constructor(public stroke: string,
              public strokeWidth?: number) {
    super();
    this.stroke = stroke;
    this.strokeWidth = !!strokeWidth ? strokeWidth : 5;
  }
}
