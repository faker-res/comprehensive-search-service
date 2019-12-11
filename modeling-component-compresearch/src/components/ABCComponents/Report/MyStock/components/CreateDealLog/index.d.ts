import * as React from 'react';
export interface ICreateDealLogProps {
  tooltip?: boolean;
  length?: number;
  lines?: number;
  style?: React.CSSProperties;
  className?: string;
}

export default class CreateDealLog extends React.Component<ICreateDealLogProps, any> {}
