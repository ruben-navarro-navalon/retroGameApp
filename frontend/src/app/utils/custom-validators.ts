import { AbstractControl, ValidationErrors } from "@angular/forms";

export class CustomValidators {
    static usernameValidator(control: AbstractControl): ValidationErrors | null {
        const value = control.value;
        const regex = /^([a-zA-Z0-9]+((\.|\-|\_)[a-zA-Z0-9]+)?)$/;
        if (!regex.test(value)){
            return {invalidusername: true };
        }
        return null;
    }
    static nameValidator(control: AbstractControl): ValidationErrors | null {
        const value = control.value;
        const regex = /^[a-zA-ZÀ-ÿ\-]+( [a-zA-ZÀ-ÿ\-]+)*$/;
        if (!regex.test(value)){
            return {invalidname: true };
        }
        return null;
    }
    static emailValidator(control: AbstractControl): ValidationErrors | null {
        const value = control.value;
        const regex = /^([a-zA-Z0-9]+((\.|\-|\_)[a-zA-Z0-9]+)*)@([a-zA-Z0-9]+((\.|\-|\_)[a-zA-Z0-9]+)*)\.[a-zA-Z]{2,4}$/;
        if (!regex.test(value)){
            return {invalidemail: true };
        }
        return null;
    }
    static passwordValidator(control: AbstractControl): ValidationErrors | null {
        const value = control.value;
        const regex = /^[\d\D]{7,}$/;
        if (!regex.test(value)){
            return {invalidpassword: true };
        }
        return null;
    }

    public static passwordMatchValidator(
        matchTo: string // name of the control to match to
      ): (AbstractControl) => ValidationErrors | null {
        return (control: AbstractControl): ValidationErrors | null => {
          return !!control.parent &&
            !!control.parent.value &&
            control.value === control.parent.controls[matchTo].value
            ? null
            : { passwordMatches: false };
        };
    }
}