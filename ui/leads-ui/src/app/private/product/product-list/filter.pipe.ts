import { Pipe, PipeTransform } from '@angular/core';
import { Item } from './product-list';

@Pipe({
  name: 'filter'
})
export class FilterPipe implements PipeTransform {
  transform(items: Item[], search: string): Item[] {
    if (!items) return [];
    if (!search) return items;

    search = search.toLowerCase();
    return items.filter(i => i.name.toLowerCase().includes(search));
  }
}
