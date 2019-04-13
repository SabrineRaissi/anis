/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { DeviceMetadataMySuffixService } from 'app/entities/device-metadata-my-suffix/device-metadata-my-suffix.service';
import { IDeviceMetadataMySuffix, DeviceMetadataMySuffix } from 'app/shared/model/device-metadata-my-suffix.model';

describe('Service Tests', () => {
    describe('DeviceMetadataMySuffix Service', () => {
        let injector: TestBed;
        let service: DeviceMetadataMySuffixService;
        let httpMock: HttpTestingController;
        let elemDefault: IDeviceMetadataMySuffix;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(DeviceMetadataMySuffixService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new DeviceMetadataMySuffix(0, 0, 'AAAAAAA', 'AAAAAAA', currentDate);
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        lastLoggedIn: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a DeviceMetadataMySuffix', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        lastLoggedIn: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        lastLoggedIn: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new DeviceMetadataMySuffix(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a DeviceMetadataMySuffix', async () => {
                const returnedFromService = Object.assign(
                    {
                        userIdDevice: 1,
                        deviceDetails: 'BBBBBB',
                        location: 'BBBBBB',
                        lastLoggedIn: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        lastLoggedIn: currentDate
                    },
                    returnedFromService
                );
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of DeviceMetadataMySuffix', async () => {
                const returnedFromService = Object.assign(
                    {
                        userIdDevice: 1,
                        deviceDetails: 'BBBBBB',
                        location: 'BBBBBB',
                        lastLoggedIn: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        lastLoggedIn: currentDate
                    },
                    returnedFromService
                );
                service
                    .query(expected)
                    .pipe(
                        take(1),
                        map(resp => resp.body)
                    )
                    .subscribe(body => expect(body).toContainEqual(expected));
                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a DeviceMetadataMySuffix', async () => {
                const rxPromise = service.delete(123).subscribe(resp => expect(resp.ok));

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush({ status: 200 });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
