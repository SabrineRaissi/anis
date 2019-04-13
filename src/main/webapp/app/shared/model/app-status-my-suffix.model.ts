export interface IAppStatusMySuffix {
    id?: number;
    designation?: string;
    code?: number;
}

export class AppStatusMySuffix implements IAppStatusMySuffix {
    constructor(public id?: number, public designation?: string, public code?: number) {}
}
