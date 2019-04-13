export interface IPossibleValueMySuffix {
    id?: number;
    value?: string;
    eligibilityId?: number;
}

export class PossibleValueMySuffix implements IPossibleValueMySuffix {
    constructor(public id?: number, public value?: string, public eligibilityId?: number) {}
}
