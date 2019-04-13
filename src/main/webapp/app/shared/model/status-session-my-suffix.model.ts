export interface IStatusSessionMySuffix {
    id?: number;
}

export class StatusSessionMySuffix implements IStatusSessionMySuffix {
    constructor(public id?: number) {}
}
