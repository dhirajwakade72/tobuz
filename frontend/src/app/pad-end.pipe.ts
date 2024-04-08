import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'padEnd'
})
export class PadEndPipe implements PipeTransform {

  transform(value: string, targetLength: number): string {
    if (!value) return value;
    if (value.length >= targetLength) return value;
    return value + ' '.repeat(targetLength - value.length);
  }

}
