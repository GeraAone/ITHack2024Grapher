import {AbstractPropertiesModel} from "./abstract-properties.model";


/**
 * Identically equals a circle model in context graphs (but of course, node is not necessary equal circle).
 */
export class NodePropertiesModel extends AbstractPropertiesModel {

  constructor(public radius: number,
              public fill: string) {
    super();
    this.radius = radius;
    this.fill = fill;
  }
}
