import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IPrivilegeMySuffix } from 'app/shared/model/privilege-my-suffix.model';
import { PrivilegeMySuffixService } from './privilege-my-suffix.service';

@Component({
    selector: 'jhi-privilege-my-suffix-update',
    templateUrl: './privilege-my-suffix-update.component.html'
})
export class PrivilegeMySuffixUpdateComponent implements OnInit {
    privilege: IPrivilegeMySuffix;
    isSaving: boolean;

    constructor(protected privilegeService: PrivilegeMySuffixService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ privilege }) => {
            this.privilege = privilege;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.privilege.id !== undefined) {
            this.subscribeToSaveResponse(this.privilegeService.update(this.privilege));
        } else {
            this.subscribeToSaveResponse(this.privilegeService.create(this.privilege));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IPrivilegeMySuffix>>) {
        result.subscribe((res: HttpResponse<IPrivilegeMySuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
