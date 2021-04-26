import { RouteComponentProps, withRouter } from 'react-router';
type FolderDetailParams = {
  id: string; 
};
type FolderDetailProps = RouteComponentProps<FolderDetailParams>;

const FolderDetail: React.FC<> = ({ match }) => {
  return 
    <div>Book ID: {match.params.id}</div>;
};
export default withRouter(FolderDetail);