export class Utility {

    public static validEmail(phone:string):boolean
    {
        if (!/\S+@\S+\.\S+/.test(phone))
        return true;        
        else 
        return false;
    }
}
