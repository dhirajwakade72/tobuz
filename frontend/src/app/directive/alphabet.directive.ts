import { Directive, ElementRef, HostListener } from '@angular/core';

@Directive({
  selector: '[appAlphabet]'
})
export class AlphabetDirective {
  
  constructor(private el: ElementRef) {}

  @HostListener('input', ['$event']) onInput(event: any): void {
    const initialValue = this.el.nativeElement.value;
    const sanitizedValue = initialValue.replace(/[^a-zA-Z\s]/g, ''); // Allow only alphabetic characters and spaces
    if (initialValue !== sanitizedValue) {
      this.el.nativeElement.value = sanitizedValue;
      event.stopPropagation();
    }
  }

}
