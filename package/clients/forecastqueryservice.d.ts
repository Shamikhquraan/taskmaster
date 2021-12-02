import {Request} from '../lib/request';
import {Response} from '../lib/response';
import {AWSError} from '../lib/error';
import {Service} from '../lib/service';
import {ServiceConfigurationOptions} from '../lib/service';
import {ConfigBase as Config} from '../lib/config-base';
interface Blob {}
declare class ForecastQueryService extends Service {
  /**
   * Constructs a service object. This object has one method for each API operation.
   */
  constructor(options?: ForecastQueryService.Types.ClientConfiguration)
  config: Config & ForecastQueryService.Types.ClientConfiguration;
  /**
   * Retrieves a forecast for a single item, filtered by the supplied criteria. The criteria is a key-value pair. The key is either item_id (or the equivalent non-timestamp, non-target field) from the TARGET_TIME_SERIES dataset, or one of the forecast dimensions specified as part of the FeaturizationConfig object. By default, QueryForecast returns the complete date range for the filtered forecast. You can request a specific date range. To get the full forecast, use the CreateForecastExportJob operation.  The forecasts generated by Amazon Forecast are in the same timezone as the dataset that was used to create the predictor. 
   */
  queryForecast(params: ForecastQueryService.Types.QueryForecastRequest, callback?: (err: AWSError, data: ForecastQueryService.Types.QueryForecastResponse) => void): Request<ForecastQueryService.Types.QueryForecastResponse, AWSError>;
  /**
   * Retrieves a forecast for a single item, filtered by the supplied criteria. The criteria is a key-value pair. The key is either item_id (or the equivalent non-timestamp, non-target field) from the TARGET_TIME_SERIES dataset, or one of the forecast dimensions specified as part of the FeaturizationConfig object. By default, QueryForecast returns the complete date range for the filtered forecast. You can request a specific date range. To get the full forecast, use the CreateForecastExportJob operation.  The forecasts generated by Amazon Forecast are in the same timezone as the dataset that was used to create the predictor. 
   */
  queryForecast(callback?: (err: AWSError, data: ForecastQueryService.Types.QueryForecastResponse) => void): Request<ForecastQueryService.Types.QueryForecastResponse, AWSError>;
}
declare namespace ForecastQueryService {
  export type Arn = string;
  export type AttributeName = string;
  export type AttributeValue = string;
  export interface DataPoint {
    /**
     * The timestamp of the specific forecast.
     */
    Timestamp?: Timestamp;
    /**
     * The forecast value.
     */
    Value?: Double;
  }
  export type DateTime = string;
  export type Double = number;
  export type Filters = {[key: string]: AttributeValue};
  export interface Forecast {
    /**
     * The forecast. The string of the string-to-array map is one of the following values:   p10   p50   p90  
     */
    Predictions?: Predictions;
  }
  export type NextToken = string;
  export type Predictions = {[key: string]: TimeSeries};
  export interface QueryForecastRequest {
    /**
     * The Amazon Resource Name (ARN) of the forecast to query.
     */
    ForecastArn: Arn;
    /**
     * The start date for the forecast. Specify the date using this format: yyyy-MM-dd'T'HH:mm:ss (ISO 8601 format). For example, 2015-01-01T08:00:00.
     */
    StartDate?: DateTime;
    /**
     * The end date for the forecast. Specify the date using this format: yyyy-MM-dd'T'HH:mm:ss (ISO 8601 format). For example, 2015-01-01T20:00:00. 
     */
    EndDate?: DateTime;
    /**
     * The filtering criteria to apply when retrieving the forecast. For example, to get the forecast for client_21 in the electricity usage dataset, specify the following:  {"item_id" : "client_21"}  To get the full forecast, use the CreateForecastExportJob operation.
     */
    Filters: Filters;
    /**
     * If the result of the previous request was truncated, the response includes a NextToken. To retrieve the next set of results, use the token in the next request. Tokens expire after 24 hours.
     */
    NextToken?: NextToken;
  }
  export interface QueryForecastResponse {
    /**
     * The forecast.
     */
    Forecast?: Forecast;
  }
  export type Statistic = string;
  export type TimeSeries = DataPoint[];
  export type Timestamp = string;
  /**
   * A string in YYYY-MM-DD format that represents the latest possible API version that can be used in this service. Specify 'latest' to use the latest possible version.
   */
  export type apiVersion = "2018-06-26"|"latest"|string;
  export interface ClientApiVersions {
    /**
     * A string in YYYY-MM-DD format that represents the latest possible API version that can be used in this service. Specify 'latest' to use the latest possible version.
     */
    apiVersion?: apiVersion;
  }
  export type ClientConfiguration = ServiceConfigurationOptions & ClientApiVersions;
  /**
   * Contains interfaces for use with the ForecastQueryService client.
   */
  export import Types = ForecastQueryService;
}
export = ForecastQueryService;
